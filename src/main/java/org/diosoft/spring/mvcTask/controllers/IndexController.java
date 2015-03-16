package org.diosoft.spring.mvcTask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    @ResponseBody
    public String index(){
        return "<h1>Index</h1>";
    }

    @RequestMapping("/hello")
    public String hello(ModelMap modelMap){
        modelMap.addAttribute("message", "Hello World");
        return "hello";
    }
}
