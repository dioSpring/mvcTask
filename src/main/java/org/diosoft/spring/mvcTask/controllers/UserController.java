package org.diosoft.spring.mvcTask.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.diosoft.spring.mvcTask.exceptions.UserLoginException;
import org.diosoft.spring.mvcTask.model.User;
import org.diosoft.spring.mvcTask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * User controller.
 *
 * @author Iryna Matusevych
 */
@RequestMapping("user")
@Component
public class UserController {

	public static final String LOGIN_FORM = "user" + File.separator + "login";
	public static final String LOGIN_VIEW_FORM = "user" + File.separator
			+ "view";
	public static final String HOLIDAY_FORM = "favorite_holiday"
			+ File.separator + "form";

	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "dateofbirth",
				new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("login")
	public String form() {
		return LOGIN_FORM;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("userForm") @Validated User user,
			BindingResult errors, final Model model) throws UserLoginException {
		if (errors.hasErrors()) {
			throw new UserLoginException();
		}

		userService.save(user);
		return LOGIN_FORM;
	}

	@ExceptionHandler(UserLoginException.class)
	public ModelAndView handleLoginExeption() {
		ModelAndView modelMap = new ModelAndView();
		modelMap.setViewName("user-do-not-autorized-exception");
		return modelMap;
	}

}
