package org.diosoft.spring.mvcTask.controllers;

import org.diosoft.spring.mvcTask.exceptions.UserAlreadyLogedInException;
import org.diosoft.spring.mvcTask.exceptions.UserLoginException;
import org.diosoft.spring.mvcTask.model.User;
import org.diosoft.spring.mvcTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;

/**
 * User controller.
 *
 * @author Iryna Matusevych
 */
@RequestMapping("/user")
@Component
public class UserController {

    public static final String QUESTIONNAIRE_LIST = "questionnaire-list";
    public static final String LOGIN_FORM = "user" + File.separator + "login";
    public static final String HOLIDAY_FORM = "favorite_holiday" + File.separator + "form";
    public static final String USER_INFO = "user" + File.separator + "info";

    @Autowired
    private UserRepository userRepository;

//    @InitBinder
//    public void initBinder(WebDataBinder binder, Errors errors) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, "dateofbirth", new CustomDateEditor(dateFormat, true));
//    }

    @RequestMapping()
    public String index(){
        return "redirect:/user/info";
    }

    @RequestMapping("login")
    public String form(@CookieValue(value = "userId", defaultValue = "") String userId, Model model) throws UserAlreadyLogedInException {
        if (userId.isEmpty() || !userRepository.exist(UUID.fromString(userId))) {
            model.addAttribute("user", new User());
            return LOGIN_FORM;
        } else {
            throw new UserAlreadyLogedInException();
        }
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute @Validated User user, BindingResult result,
                       @CookieValue(value = "userId", defaultValue = "") String userId,
                       HttpServletResponse response) throws UserAlreadyLogedInException {

        if (!userId.isEmpty() && userRepository.exist(UUID.fromString(userId))) {
            throw new UserAlreadyLogedInException();
        } else if (result.hasErrors()) {
            return LOGIN_FORM;
        } else {
            Cookie cookie = new Cookie("userId", user.getId().toString());
            cookie.setPath("/");
            response.addCookie(cookie);
            userRepository.add(user);
        }
        return "redirect:/user";
    }

    @RequestMapping("info")
    public String info(@CookieValue(value = "userId", defaultValue = "") String userId, ModelMap modelMap) throws UserLoginException {
        if (userId.isEmpty() || !userRepository.exist(UUID.fromString(userId))) {
            System.out.println("UUIDCCC: "+userId);
            return "redirect:login";
        } else {
            User user = userRepository.get(UUID.fromString(userId));
            modelMap.addAttribute("user", user);
            return USER_INFO;
        }
    }

    @RequestMapping("logout")
    public String logout(@CookieValue(value = "userId", defaultValue = "") String userId, ModelMap modelMap, HttpServletResponse response) {
        if (!userId.isEmpty() && userRepository.exist(UUID.fromString(userId))) {
            userRepository.remove(UUID.fromString(userId));
            Cookie cookie = new Cookie("userId", "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        modelMap.addAttribute("user", new User());
        return LOGIN_FORM;
    }

    @ExceptionHandler(UserLoginException.class)
    public ModelAndView handleLoginExeption() {
        ModelAndView modelMap = new ModelAndView();
        modelMap.setViewName("user-do-not-autorized-exception");
        return modelMap;
    }

    @ExceptionHandler(UserAlreadyLogedInException.class)
    public ModelAndView alreadyLogedIn() {
        ModelAndView modelMap = new ModelAndView();
        modelMap.setViewName("already-loged-in-exception");
        return modelMap;
    }
}
