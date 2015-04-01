package org.diosoft.spring.mvcTask.controllers;

import org.diosoft.spring.mvcTask.dto.LoginDto;
import org.diosoft.spring.mvcTask.exceptions.UserAlreadyLogedInException;
import org.diosoft.spring.mvcTask.exceptions.UserLoginException;
import org.diosoft.spring.mvcTask.model.User;
import org.diosoft.spring.mvcTask.model.UserSession;
import org.diosoft.spring.mvcTask.services.UserService;
import org.diosoft.spring.mvcTask.services.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@Controller
@RequestMapping("/user")
public class UserController {

    public static final String QUESTIONNAIRE_LIST = "questionnaire-list";
    public static final String SIGNUP_FORM = "user" + File.separator + "signup";
    public static final String LOGIN_FORM = "user" + File.separator + "login";
    public static final String HOLIDAY_FORM = "favorite_holiday" + File.separator + "form";
    public static final String USER_INFO = "user" + File.separator + "info";

    @Autowired
    private UserService userService;

    @Autowired
    private UserSessionService userSessionService;


//    @InitBinder
//    public void initBinder(WebDataBinder binder, Errors errors) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, "dateofbirth", new CustomDateEditor(dateFormat, true));
//    }

    @RequestMapping()
    public String index() {
        return "redirect:/user/info";
    }

    @RequestMapping("signup")
    public String signupForm(Model model, HttpServletResponse response) throws UserAlreadyLogedInException {
        Cookie cookie = new Cookie("userId", UUID.randomUUID().toString());
        cookie.setPath("/");
        response.addCookie(cookie);
        model.addAttribute("user", new User());
        return SIGNUP_FORM;
    }

    @RequestMapping("login")
    public String loginForm(Model model, HttpServletResponse response) throws UserAlreadyLogedInException {
        Cookie cookie = new Cookie("userId", UUID.randomUUID().toString());
        cookie.setPath("/");
        response.addCookie(cookie);
        model.addAttribute("loginDto", new LoginDto());
        return LOGIN_FORM;
    }


    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute @Validated User user, BindingResult result,
                         @CookieValue(value = "userId", defaultValue = "") String userId,
                         HttpServletResponse response) throws UserAlreadyLogedInException {

        if (userId.isEmpty()) return "redirect:/user/signup";
        if (result.hasErrors()) {
            return SIGNUP_FORM;
        } else {
            UserSession userSession = new UserSession();
            userSession.setSessionId(userId);
//            user.getSessions().add(userSession);
            userSession.setUser(user);
//            userService.save(user);
            userSessionService.save(userSession);
        }
        return "redirect:/user";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Validated LoginDto loginDto, BindingResult result,
                        @CookieValue(value = "userId", defaultValue = "") String userId,
                        HttpServletResponse response) {
        if (result.hasErrors()) {
            return LOGIN_FORM;
        } else {
//            User user = userDAO.findByUsername(loginDto.getUsername());
//            System.out.println(user);
//            System.out.println(loginDto);
//            if (user != null && user.getPassword().equals(loginDto.getPassword())) {
//                UserSession session = new UserSession();
//                session.setUser(user);
//                userSessioDAO.save(session);
//                System.out.println(session);
//                Cookie cookie = new Cookie("userSessionId", session.getSessionId());
//                cookie.setPath("/");
//                response.addCookie(cookie);
//            }
        }
        return "redirect:/user";
    }


    @RequestMapping("info")
    public String info(@CookieValue(value = "userId", defaultValue = "") String sessionId, ModelMap modelMap) throws UserLoginException {
        if (!sessionId.isEmpty()) {
            System.out.println(sessionId);
            UserSession session = userSessionService.find(sessionId);
            if (session != null&&session.getUser()!=null) {
                modelMap.addAttribute("user", session.getUser());
                return USER_INFO;
            }
        }
        return "redirect:/user/signup";
    }
//
//    @RequestMapping("logout")
//    public String logout(@CookieValue(value = "userId", defaultValue = "") String userId, ModelMap modelMap, HttpServletResponse response) {
//        if (!userId.isEmpty() && userRepository.exist(UUID.fromString(userId))) {
//            userRepository.remove(UUID.fromString(userId));
//            Cookie cookie = new Cookie("userId", "");
//            cookie.setPath("/");
//            cookie.setMaxAge(0);
//            response.addCookie(cookie);
//        }
//        modelMap.addAttribute("user", new User());
//        return LOGIN_FORM;
//    }

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
