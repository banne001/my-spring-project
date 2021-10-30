package edu.greenriver.student.myspringproject.controllers;

import edu.greenriver.student.myspringproject.models.Restaurant;
import edu.greenriver.student.myspringproject.services.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Index Controller to route users to correct web pages
 *
 * @author blezyl santos
 * @version 1.1
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

    @GetMapping("restaurant/addRestaurant")
    public String loadForm(Model model){
        model.addAttribute("title", "Add a New Restaurant!");
        model.addAttribute("restaurant", new Restaurant());
        model.addAttribute("redirectTo", "/bored/restaurant/addRestaurant");
        return "add-restaurant";
    }

    @PostMapping("restaurant/addRestaurant")
    public String handleForm(@ModelAttribute Restaurant res){
        System.out.println("Posted from form " + res);

        service.save(res);
        return "redirect:/bored/summary";
    }

    @GetMapping("restaurant/editRestaurant/{id}")
    public String editForm(Model model, @PathVariable int id){
        model.addAttribute("title", "Update Restaurant");
        model.addAttribute("restaurant", service.findById(id));
        model.addAttribute("redirectTo", "/bored/restaurant/editRestaurant/" + id);

        return "add-restaurant";
    }

    @PostMapping("restaurant/editRestaurant/{id}")
    public String handleEditForm(@ModelAttribute Restaurant res){
        System.out.println("Posted from form " + res);

        service.save(res);
        return "redirect:/bored/summary";
    }

    @RequestMapping("restaurant/deleteRestaurant/{id}")
    public String deleteRestaurant(@PathVariable int id){
        service.deleteByID(id);
        return "redirect:/bored/summary";
    }
}
