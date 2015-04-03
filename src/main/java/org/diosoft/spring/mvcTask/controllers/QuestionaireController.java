package org.diosoft.spring.mvcTask.controllers;

import org.diosoft.spring.mvcTask.services.QuestionaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questionaire")
public class QuestionaireController {

    @Autowired
    private QuestionaireService questionaireService;



}
