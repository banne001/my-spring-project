package edu.greenriver.student.myspringproject.controllers;

import edu.greenriver.student.myspringproject.models.Restaurant;
import edu.greenriver.student.myspringproject.services.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String summary(Model model){
        model.addAttribute("restaurants", new RestaurantService().allRestaurants());

        return "summary";
    }

    @RequestMapping("restaurant/{name}")
    public String element(Model model, @PathVariable String name){
        model.addAttribute("specificRest", new RestaurantService().findByName(name));

        return "element";
    }

    @RequestMapping("restaurant/random")
    public String random(Model model){
        model.addAttribute("specificRest", new RestaurantService().random());

        return "element";
    }
}
