package edu.greenriver.student.myspringproject.controllers;

import edu.greenriver.student.myspringproject.models.Activity;
import edu.greenriver.student.myspringproject.models.Restaurant;
import edu.greenriver.student.myspringproject.services.ActivityService;
import edu.greenriver.student.myspringproject.services.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

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
    private ActivityService activityService;

    public IndexController(RestaurantService service, ActivityService activityService) {
        this.service = service;
        this.activityService = activityService;
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
    @RequestMapping("restaurants/summary")
    public String summary(Model model){
        model.addAttribute("restaurant", service.allRestaurants());

        return "summary";
    }

    /**
     * Summary page to view all Activities
     * @return html page
     */
    @RequestMapping("activities/summary")
    public String summaryActivities(Model model){
        model.addAttribute("activities", activityService.allActivity());

        return "summaryActivities";
    }

    /**
     * Restaurant page to view a specific page given the id
     * of the restaurant
     * @return html page
     */
    @RequestMapping("restaurant/{id}")
    public String element(Model model, @PathVariable int id){
        model.addAttribute("specificRest", service.findById(id));

        return "element";
    }

    /**
     * Activity page to view a specific page given the id
     * of the Activity
     * @return html page
     */
    @RequestMapping("activity/{id}")
    public String elementActivity(Model model, @PathVariable int id){
        model.addAttribute("specificRest", activityService.findById(id));

        return "elementActivity";
    }

    /**
     * Restaurant page that gets a random restaurant from the database and
     * returns the html page to show the random restaurant
     * @return html page
     */
    @RequestMapping("/random")
    public String random(Model model){
        Random random = new Random();
        if(random.nextInt(500) > 250){
            model.addAttribute("specificRest", service.random());
            return "element";
        }
        model.addAttribute("specificRest", activityService.random());
        return "elementActivity";
    }

    @GetMapping("restaurant/addRestaurant")
    public String loadForm(Model model){
        model.addAttribute("title", "Add a New Restaurant!");
        model.addAttribute("restaurant", new Restaurant());
        model.addAttribute("redirectTo", "/bored/restaurant/addRestaurant");
        return "add-restaurant";
    }

    @GetMapping("activity/addActivity")
    public String loadFormActivity(Model model){
        model.addAttribute("title", "Add a New Activity!");
        model.addAttribute("activity", new Activity());
        model.addAttribute("redirectTo", "/bored/activity/addActivity");
        return "add-activity";
    }

    @PostMapping("restaurant/addRestaurant")
    public String handleForm(@ModelAttribute Restaurant res){
        System.out.println("Posted from form " + res);

        service.save(res);
        return "redirect:/bored/restaurants/summary";
    }

    @PostMapping("activity/addActivity")
    public String handleFormActivity(@ModelAttribute Activity activity){
        System.out.println("Posted from form " + activity);

        activityService.save(activity);
        return "redirect:/bored/activities/summary";
    }

    @GetMapping("restaurant/editRestaurant/{id}")
    public String editForm(Model model, @PathVariable int id){
        model.addAttribute("title", "Update Restaurant");
        model.addAttribute("restaurant", service.findById(id));
        model.addAttribute("redirectTo", "/bored/restaurant/editRestaurant/" + id);

        return "add-restaurant";
    }

    @GetMapping("activity/editActivity/{id}")
    public String editFormActivity(Model model, @PathVariable int id){
        model.addAttribute("title", "Update Activity");
        model.addAttribute("activity", activityService.findById(id));
        model.addAttribute("redirectTo", "/bored/activity/editActivity/" + id);

        return "add-activity";
    }

    @PostMapping("restaurant/editRestaurant/{id}")
    public String handleEditForm(@ModelAttribute Restaurant res){
        System.out.println("Posted from form RESTAURANT" + res);

        service.save(res);
        return "redirect:/bored/restaurants/summary";
    }

    @PostMapping("activity/editActivity/{id}")
    public String handleEditFormActivity(@ModelAttribute Activity activity){
        System.out.println("Posted from form ACTIVITY" + activity);

        activityService.save(activity);
        return "redirect:/bored/activities/summary";
    }

    @RequestMapping("restaurant/deleteRestaurant/{id}")
    public String deleteRestaurant(@PathVariable int id){
        service.deleteByID(id);
        return "redirect:/bored/restaurants/summary";
    }

    @RequestMapping("activity/deleteActivity/{id}")
    public String deleteActivity(@PathVariable int id){
        activityService.deleteByID(id);
        return "redirect:/bored/activities/summary";
    }

    @GetMapping("restaurants")
    public String getWebApiRestaurants(){
        return "restaurants.html";
    }

    @GetMapping("activities")
    public String getWebApiActivities(){
        return "activities.html";
    }

    @GetMapping("admin")
    public String getAdmin(){
        return "admin.html";
    }
}
