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
import java.util.Date;
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

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginForm(Model model, HttpServletResponse response, @CookieValue(value = "sessionId", defaultValue = "") String sessionId) throws UserAlreadyLogedInException {
        if(!sessionId.isEmpty()){
            throw new UserAlreadyLogedInException();
        }
        Cookie cookie = new Cookie("sessionId", UUID.randomUUID().toString());
        cookie.setPath("/");
        response.addCookie(cookie);
        model.addAttribute("loginDto", new LoginDto());
        return LOGIN_FORM;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Validated LoginDto loginDto, BindingResult result, @CookieValue(value = "sessionId", defaultValue = "") String sessionId, Model model, HttpServletResponse response) {
        if (result.hasErrors()) {
            return LOGIN_FORM;
        } else {

            User user = userService.get(loginDto);
            if (user == null) {
                return "redirect:/" + SIGNUP_FORM;
            }
            UserSession userSession = new UserSession();
            userSession.setSessionId(sessionId);
            userSession.setLastConnected(new Date());
            userSession.setUser(user);

            userSessionService.save(userSession);

        }

        return "redirect:/" + USER_INFO;
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signupForm(Model model, HttpServletResponse response) throws UserAlreadyLogedInException {
        //IM TODO:tranfer LoginDTO
        model.addAttribute("user", new User());
        return SIGNUP_FORM;
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute @Validated User user, BindingResult result, @CookieValue(value = "sessionId", defaultValue = "") String sessionId, HttpServletResponse response)
            throws UserAlreadyLogedInException {

        if (sessionId.isEmpty())
            return "redirect:/user/signup";
        if (result.hasErrors()) {
            return SIGNUP_FORM;
        } else {
            UserSession userSession = new UserSession();
            userSession.setSessionId(sessionId);
            userSession.setUser(user);
            userSessionService.save(userSession);
        }
        return "redirect:/" + USER_INFO;
    }

    @RequestMapping("info")
    public String info(@CookieValue(value = "sessionId", defaultValue = "") String sessionId, ModelMap modelMap) throws UserLoginException {

        modelMap.addAttribute("user", userSessionService.find(sessionId).getUser());
        return USER_INFO;

    }

    @RequestMapping("logout")
    public String logout(@CookieValue(value = "sessionId", defaultValue = "") String sessionId, ModelMap modelMap, HttpServletResponse response) {
        if (!sessionId.isEmpty()) {
            Cookie cookie = new Cookie("sessionId", "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        modelMap.addAttribute("loginDto", new LoginDto());
        return "redirect:/"+LOGIN_FORM;
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
