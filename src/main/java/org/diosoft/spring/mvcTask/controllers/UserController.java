package org.diosoft.spring.mvcTask.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.diosoft.spring.mvcTask.model.User;
import org.diosoft.spring.mvcTask.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User controller.
 *
 * @author Iryna Matusevych
 */
@RequestMapping("user")
@Component
public class UserController {

	public static final String LOGIN_FORM = "user" + File.separator + "login";
	public static final String HOLIDAY_FORM = "favorite_holiday" + File.separator + "form";

	@Autowired
	private HolidayService holidayService;

	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "dateofbirth", new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("login")
	public String form() {
		return LOGIN_FORM;
	}


	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(final User user, @CookieValue(value="userId", defaultValue = "") String userId,
			HttpServletResponse response) {

		if (userId.isEmpty()) {
			response.addCookie(new Cookie("userId", user.getId().toString()));
		}

		return "redirect:/"+HOLIDAY_FORM;
	}

}
