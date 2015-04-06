package org.diosoft.spring.mvcTask.controllers;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/")
public class IndexController {
	/**
	 * View name : form.
	 */
//	public static final String LOGIN_PAGE = "user" + File.separator + "login";;
	public static final String USER_PAGE = "user";

	@RequestMapping ()
	public String index() {
		return "redirect:/"+USER_PAGE;
	}
}
