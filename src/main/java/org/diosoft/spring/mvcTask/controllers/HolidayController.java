package org.diosoft.spring.mvcTask.controllers;

import java.io.File;

import org.diosoft.spring.mvcTask.model.HolidayBO;
import org.diosoft.spring.mvcTask.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * holiday controller.
 *
 * @author Iryna Matusevych
 */
@RequestMapping("favorite_holiday")
@Component
public class HolidayController {

	public static final String VIEW_NAME_FORM = "favorite_holiday" + File.separator + "form";
	public static final String VIEW_RESULT_FORM = "favorite_holiday" + File.separator + "result";

	@Autowired
	private HolidayService holidayService;

	@RequestMapping("form")
	public String form() {
		return VIEW_NAME_FORM;
	}

	@RequestMapping("result")
	public String result() {
		return VIEW_RESULT_FORM;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("holidayForm") final HolidayBO answer, @CookieValue(value="userId") String userId) {
		
		if(userId.isEmpty()){
			//throw exeption or smth else
		}
		
		holidayService.save(answer);

		return "result";
	}

}
