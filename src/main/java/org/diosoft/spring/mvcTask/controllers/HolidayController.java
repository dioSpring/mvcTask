package org.diosoft.spring.mvcTask.controllers;

import java.io.File;

import org.diosoft.spring.mvcTask.services.HolidayBO;
import org.diosoft.spring.mvcTask.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String save(@RequestParam("memorableHoliday") final String memorableHoliday,
			@RequestParam("unlikedHoliday") final String unlikedHoliday, final ModelMap modelMap) {

		// IM TODO:add validation
		HolidayBO answer = new HolidayBO();
		answer.setMemorableHoliday(memorableHoliday);
		answer.setUnlikedHoliday(unlikedHoliday);

		holidayService.save(answer);

		return VIEW_RESULT_FORM;
	}

}
