package edu.greenriver.student.myspringproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Index Controller to route users to correct web pages
 *
 * @author blezyl santos
 * @version 1.0
 */
@Controller
@RequestMapping("bored")
public class IndexController {

    /**
     * Map for main pages
     * include "", "/", "index", "index.html"
     * @return html page
     */
    @RequestMapping(value = {"", "/", "index", "index.html"})
    public String home() {
        return "home";
    }

    @RequestMapping("summary")
    public String summary(){
        return "summary";
    }

    @RequestMapping("element")
    public String element(){
        return "element";
    }
}
