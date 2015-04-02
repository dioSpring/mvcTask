package org.diosoft.spring.mvcTask.controllers;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.diosoft.spring.mvcTask.dto.LoginDto;
import org.diosoft.spring.mvcTask.exceptions.UserWithThisNameAlreadyExists;
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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public String loginForm(@ModelAttribute("user") LoginDto loginDto, HttpServletResponse response, @CookieValue(value = "sessionId", defaultValue = "") String sessionId) {
		if (!sessionId.isEmpty()) {
			return "redirect:/" + USER_INFO;
		}
		return LOGIN_FORM;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") @Validated LoginDto loginDto, BindingResult result, @CookieValue(value = "sessionId", defaultValue = "") String sessionId, Model model, HttpServletResponse response) {
		if (result.hasErrors()) {
			return LOGIN_FORM;
		} else {

			User user = userService.get(loginDto.getUsername(),loginDto.getPassword());
			if (user == null) {
				return "redirect:/" + SIGNUP_FORM;
			}

			UserSession userSession = new UserSession();
			userSession.setSessionId(UUID.randomUUID().toString());
			userSession.setLastConnected(new Date());
			userSession.setUser(user);

			userSessionService.save(userSession);

			Cookie cookie = new Cookie("sessionId", userSession.getSessionId());
			cookie.setPath("/");
			response.addCookie(cookie);
		}

		return "redirect:/" + USER_INFO;
	}

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signupForm(@ModelAttribute("user") User user, @CookieValue(value = "sessionId", defaultValue = "") String sessionId, HttpServletResponse response) {
		if (!sessionId.isEmpty()) {
			return "redirect:/" + USER_INFO;
		}
		return SIGNUP_FORM;
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("user") @Validated User user, BindingResult result, @CookieValue(value = "sessionId", defaultValue = "") String sessionId, HttpServletResponse response) throws UserWithThisNameAlreadyExists {

		if (result.hasErrors()) {
			return SIGNUP_FORM;
		} else {
			if(userService.checkUserName(user.getUsername())){
				throw new UserWithThisNameAlreadyExists();
			}
			UserSession userSession = new UserSession();
			userSession.setSessionId(UUID.randomUUID().toString());
			userSession.setUser(user);
			userSessionService.save(userSession);

			Cookie cookie = new Cookie("sessionId", userSession.getSessionId());
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		return "redirect:/" + USER_INFO;
	}

	@RequestMapping("info")
	public String info(@CookieValue(value = "sessionId", defaultValue = "") String sessionId, ModelMap modelMap) {
		if (sessionId.isEmpty()) {
			return "redirect:/" + LOGIN_FORM;
		}
		modelMap.addAttribute("user", userSessionService.find(sessionId).getUser());
		return USER_INFO;

	}

	@RequestMapping("logout")
	public String logout(@CookieValue(value = "sessionId", defaultValue = "") String sessionId,  ModelMap modelMap, HttpServletResponse response) {
		if (!sessionId.isEmpty()) {
			Cookie sessionIdCookies = new Cookie("sessionId", "");
			sessionIdCookies.setPath("/");
			sessionIdCookies.setMaxAge(0);
			response.addCookie(sessionIdCookies);
		}
		return "redirect:/" + LOGIN_FORM;
	}

	@ExceptionHandler(UserWithThisNameAlreadyExists.class)
	public ModelAndView userWithThisNameAlreadyExists() {
		ModelAndView modelMap = new ModelAndView();
		modelMap.setViewName("user-already-exists-exception");
		return modelMap;
	}
}
