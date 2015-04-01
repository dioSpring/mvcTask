package org.diosoft.spring.mvcTask.controllers;

import org.diosoft.spring.mvcTask.exceptions.UserAlreadyLogedInException;
import org.diosoft.spring.mvcTask.model.AboutSpace;
import org.diosoft.spring.mvcTask.model.User;
import org.diosoft.spring.mvcTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Created by yar on 26.03.15.
 */
@Controller
@RequestMapping("aboutspace")
public class AboutSpaceController {

    public static final String ABOUT_SPACE_FORM = "space" + File.separator + "about-space-form";
    public static final String ABOUT_SPACE_RESULT = "space" + File.separator + "about-space-result";

//    @Autowired
//    private UserRepository userRepository;

    @RequestMapping
    public String index(){
        return "redirect:/aboutspace/form";
    }

    @RequestMapping("form")
    public String form(Model model, @CookieValue(value = "userId", defaultValue = "") String userId){
        model.addAttribute("aboutSpace", new AboutSpace());
        return ABOUT_SPACE_FORM;
    }

//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public String save(@ModelAttribute @Validated AboutSpace aboutSpace, BindingResult result,
//                       @CookieValue(value = "userId", defaultValue = "") String userId,
//                       HttpServletResponse response) {
//        if (userId.isEmpty()) {
//            return "redirect:/user";
//        } else if (result.hasErrors()) {
//            return ABOUT_SPACE_FORM;
//        } else {
////            User user = userRepository.get(UUID.fromString(userId));
////            System.out.println("HHHHH: "+userId);
////            System.out.println(user);
////            user.getQuestionaireMap().put(aboutSpace.getId(), aboutSpace);
//        }
//        return "redirect:/user";
//    }

//    @RequestMapping("result")
//    public Callable<String> result(@CookieValue(value = "userId", defaultValue = "") final String userId, @RequestParam("id") final String id, final Model model){
//        return new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                if (userId.isEmpty()||id.isEmpty()) {
//                    return "redirect:/user";
//                } else {
//                    User user = userRepository.get(UUID.fromString(userId));
////                    AboutSpace aboutSpace = (AboutSpace) user.getQuestionaireMap().get(UUID.fromString(id));
////                    model.addAttribute("aboutSpace", aboutSpace);
//                    return ABOUT_SPACE_RESULT;
//                }
//            }
//        };
//    }

}
