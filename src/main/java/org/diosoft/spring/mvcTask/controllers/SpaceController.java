package org.diosoft.spring.mvcTask.controllers;

import java.io.File;

import org.diosoft.spring.mvcTask.model.SpaceQuestionaire;
import org.diosoft.spring.mvcTask.model.UserSession;
import org.diosoft.spring.mvcTask.services.SpaceQuestionaireService;
import org.diosoft.spring.mvcTask.services.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("space")
public class SpaceController {

    public static final String ABOUT_SPACE_FORM = "space" + File.separator + "about-space-form";
    public static final String ABOUT_SPACE_RESULT = "space" + File.separator + "about-space-result";

    @Autowired
    private SpaceQuestionaireService spaceQuestionaireService;

    @Autowired
    private UserSessionService userSessionService;

    @RequestMapping
    public String index(){
        return "redirect:/space/form";
    }

    @RequestMapping("form")
    public String form(Model model, @CookieValue(value = "userId", defaultValue = "") String userId){
        model.addAttribute("spaceQuestionaire", new SpaceQuestionaire());
        return ABOUT_SPACE_FORM;
    }



    @RequestMapping("result")
    public String result(Model model, @CookieValue(value = "sessionId", defaultValue = "") String sessionId, @RequestParam("id")final Long id){
        if(!sessionId.isEmpty()){
            SpaceQuestionaire spaceQuestionaire = spaceQuestionaireService.get(id);
            System.out.println(spaceQuestionaire);
            model.addAttribute("spaceQuestionaire", spaceQuestionaire);
            return ABOUT_SPACE_RESULT;
        }
        return "redirect:/user";
    }



    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute @Validated SpaceQuestionaire spaceQuestionaire, BindingResult result,
                       @CookieValue(value = "sessionId", defaultValue = "") String sessionId,
                       HttpServletResponse response) {
        if (sessionId.isEmpty()) {
            return "redirect:/user";
        } else if (result.hasErrors()) {
            return ABOUT_SPACE_FORM;
        } else {
            UserSession userSession = userSessionService.find(sessionId);
            if(userSession!=null){
                userSession.getUser().getQuestionaires().add(spaceQuestionaire);
                userSessionService.save(userSession);
            }
        }
        return "redirect:/user/info";
    }

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
