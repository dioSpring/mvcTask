package org.diosoft.spring.mvcTask.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.diosoft.spring.mvcTask.exceptions.UserLoginException;
import org.diosoft.spring.mvcTask.model.HolidayBO;
import org.diosoft.spring.mvcTask.model.User;
import org.diosoft.spring.mvcTask.services.HolidayService;
import org.diosoft.spring.mvcTask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

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
    public static final String LOGIN_FORM = "favorite_holiday" + File.separator + "login";

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, "dateofbirth", new CustomDateEditor(dateFormat, true));
    }

    @ExceptionHandler(UserLoginException.class)
    public ModelAndView handleLoginExeption() {
        ModelAndView modelMap = new ModelAndView();
        modelMap.setViewName("user-do-not-autorized-exception");
        return modelMap;
    }

    @RequestMapping("login")
    public String login() {
        return LOGIN_FORM;
    }

    @RequestMapping(value = "authorization", method = RequestMethod.POST)
    public String authorization(@ModelAttribute("userForm") @Validated final User user, @CookieValue(value = "userId", defaultValue = "") final String userId, final HttpServletResponse response,  final BindingResult errors, final Model model) throws UserLoginException {
        if (errors.hasErrors()) {
            throw new UserLoginException();
        }

        response.addCookie(new Cookie("userId", user.getId().toString()));

        userService.save(user);
        return "redirect:/" + VIEW_NAME_FORM;
    }

    @RequestMapping(value = "form", method = { RequestMethod.POST, RequestMethod.GET })
    public String holidayForm() {

        return VIEW_NAME_FORM;
    }

    @RequestMapping("result")
    public String result() {
        return VIEW_RESULT_FORM;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public DeferredResult<String> saveHolidayResult(@ModelAttribute("holidayForm") final HolidayBO answer, @CookieValue(value = "userId") final String userId, ModelMap model) {

        final DeferredResult<String> task = new DeferredResult<>();
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(UUID.fromString(userId));
                answer.setUserId(UUID.fromString(userId));
                holidayService.save(answer);
                task.setResult("redirect:result");
            }
        });
        return task;

    }

}
