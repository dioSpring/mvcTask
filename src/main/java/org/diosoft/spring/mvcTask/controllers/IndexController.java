package org.diosoft.spring.mvcTask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/")
public class IndexController {
	/**
	 * View name : form.
	 */
	public static final String VIEW_NAME_INDEX_PAGE = "hello";

	@RequestMapping ()
	public String hello() {
		return VIEW_NAME_INDEX_PAGE;
	}
}
