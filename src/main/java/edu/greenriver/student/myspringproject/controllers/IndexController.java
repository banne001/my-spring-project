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

    private RestaurantService service;

    public IndexController(RestaurantService service) {
        this.service = service;
    }

    /**
     * Map for main pages
     * include "", "/", "index", "index.html"
     * @return html page
     */
    @RequestMapping(value = {"", "/", "index", "index.html"})
    public String home() {
        return "home";
    }

    /**
     * Summary page to view all restaurants
     * @return html page
     */
    @RequestMapping("summary")
    public String summary(Model model){
        model.addAttribute("restaurants", service.allRestaurants());

        return "summary";
    }
    /**
     * Restaurant page to view a specific page given the name
     * of the restaurant
     * @return html page
     */
    @RequestMapping("restaurant/{id}")
    public String element(Model model, @PathVariable int id){
        model.addAttribute("specificRest", service.findById(id));

        return "element";
    }

    /**
     * Restaurant page that gets a random restaurant from the database and
     * returns the html page to show the random restaurant
     * @return html page
     */
    @RequestMapping("restaurant/random")
    public String random(Model model){
        model.addAttribute("specificRest", service.random());

        return "element";
    }
}
