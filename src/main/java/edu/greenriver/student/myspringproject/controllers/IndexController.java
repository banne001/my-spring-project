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
}
