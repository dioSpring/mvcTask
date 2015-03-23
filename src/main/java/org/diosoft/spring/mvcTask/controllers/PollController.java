package org.diosoft.spring.mvcTask.controllers;

import java.io.File;

import org.diosoft.spring.mvcTask.services.PollService;
import org.diosoft.spring.mvcTask.model.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	 * Param name : poll results.
	 */
	private static final String PARAM_POLL_RESULTS = "pollResults";

	@Autowired
	private PollService pollService;

	/**
	 * Show poll form.
	 */
	@RequestMapping("form")
	public String showForm(final Model model){
		model.addAttribute("userBO", new UserBO());
		return VIEW_NAME_FORM;
	}

	/**
	 * Save users poll results
	 *
	 * @return model name to render
	 */
	@RequestMapping (value = "save", method = RequestMethod.POST)
	public String save(
			@Validated final UserBO user,
			final BindingResult errors,
			final Model model) {

		if (errors.hasErrors()) {
			return VIEW_NAME_FORM;
		}

		pollService.save(user);

		return "redirect:results";
	}

	@RequestMapping (value = "results", method = RequestMethod.GET)
	public String getPollResults(final Model modelMap) {
		modelMap.addAttribute(PARAM_POLL_RESULTS, pollService.getUsers());

		return VIEW_NAME_POLL_RESULTS;
	}

}
