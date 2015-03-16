package org.diosoft.spring.mvcTask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yar on 16.03.15.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @ResponseBody
    public String index(){
        return "<h1>Hello World</h1>";
    }

}
