package org.diosoft.spring.mvcTask.controllers;

import java.io.File;

import org.diosoft.spring.mvcTask.services.PollService;
import org.diosoft.spring.mvcTask.services.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Poll controller.
 *
 * @author Vasyl Zarva
 */
@RequestMapping ("poll/zarva")
@Component
public class PollController {
	/**
	 * View name : form.
	 */
	public static final String VIEW_NAME_FORM = "zarva" + File.separator + "poll-form";
	/**
	 * View name : poll results.
	 */
	public static final String VIEW_NAME_POLL_RESULTS = "zarva" + File.separator + "poll-results";

	/**
	 * Error param name : first name is empty.
	 */
	private static final String ERROR_FIRST_NAME_IS_EMPTY = "lastNameIsEmpty";
	/**
	 * Error param name : age is empty.
	 */
	private static final String ERROR_AGE_IS_EMPTY = "ageIsEmpty";
	/**
	 * Error param name : start smoke age is empty.
	 */
	private static final String ERROR_START_SMOKE_AGE_IS_EMPTY = "startSmokeAgeIsEmpty";
	/**
	 * Error param name : cigarette per day is empty.
	 */
	private static final String ERROR_CIGARETTE_PER_DAY_IS_EMPTY = "cigarettePerDayIsEmpty";

	/**
	 * Param name : poll results.
	 */
	private static final String PARAM_POLL_RESULTS = "pollResults";

	@Autowired
	private PollService pollService;

	/**
	 * Show poll form.
	 */
	@RequestMapping("form")
	public String showForm(){
		return VIEW_NAME_FORM;
	}

	/**
	 * Save users poll results
	 *
	 * @return model name to render
	 */
	@RequestMapping (value = "save", method = RequestMethod.POST)
	public String save(
			@RequestParam ("firstname") final String firstName,
			@RequestParam ("age") final Integer age,
			@RequestParam ("startToSmokeAge") final Integer startToSmokeAge,
			@RequestParam ("cigarettePerDay") final Integer cigarettePerDay,
			final ModelMap modelMap) {

		boolean hasErrors = false;
		if (StringUtils.isEmpty(firstName)) {
			modelMap.addAttribute(ERROR_FIRST_NAME_IS_EMPTY, true);
			hasErrors = true;
		}
		if (age == null) {
			modelMap.addAttribute(ERROR_AGE_IS_EMPTY, true);
			hasErrors = true;
		}
		if (startToSmokeAge == null) {
			modelMap.addAttribute(ERROR_START_SMOKE_AGE_IS_EMPTY, true);
			hasErrors = true;
		}
		if (cigarettePerDay == null) {
			modelMap.addAttribute(ERROR_CIGARETTE_PER_DAY_IS_EMPTY, true);
			hasErrors = true;
		}
		if (hasErrors)
			return VIEW_NAME_FORM;

		UserBO user = new UserBO();
		user.setFirstName(firstName);
		user.setAge(age);
		user.setStartToSmokeAge(startToSmokeAge);
		user.setCigarettePerDay(cigarettePerDay);
		pollService.save(user);

		return "redirect:results";
	}

	@RequestMapping (value = "results", method = RequestMethod.GET)
	public String getPollResults(final ModelMap modelMap) {
		modelMap.addAttribute(PARAM_POLL_RESULTS, pollService.getUsers());

		return VIEW_NAME_POLL_RESULTS;
	}

}
