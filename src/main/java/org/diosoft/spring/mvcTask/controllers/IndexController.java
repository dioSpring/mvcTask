package org.diosoft.spring.mvcTask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/")
public class IndexController {
	/**
	 * View name : form.
	 */
	public static final String VIEW_NAME_INDEX_PAGE = "questionnaire-list";

	@RequestMapping ()
	public String index() {
		return VIEW_NAME_INDEX_PAGE;
	}
}
