package edu.greenriver.student.myspringproject.controllers;

import edu.greenriver.student.myspringproject.models.Activity;
import edu.greenriver.student.myspringproject.models.Authority;
import edu.greenriver.student.myspringproject.models.Restaurant;
import edu.greenriver.student.myspringproject.models.User;
import edu.greenriver.student.myspringproject.services.ActivityService;
import edu.greenriver.student.myspringproject.services.LoginService;
import edu.greenriver.student.myspringproject.services.RestaurantService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
    private LoginService loginService;

    /**
     * @param service The Service for the restaurant
     * @param activityService the service for the activities
     */
    public IndexController(RestaurantService service, ActivityService activityService, LoginService loginService) {
        this.service = service;
        this.activityService = activityService;
        this.loginService = loginService;
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
     * @param model to get variables to the page
     * @return html page the html template page
     */
    @RequestMapping("restaurants/summary")
    public String summary(Model model){
        model.addAttribute("restaurant", service.allRestaurants());

        return "summary";
    }

    /**
     * Summary page to view all Activities
     * @param model to get variables to the page
     * @return html page the html template page
     */
    @RequestMapping("activities/summary")
    public String summaryActivities(Model model){
        model.addAttribute("activities", activityService.allActivity());

        return "summaryActivities";
    }

    /**
     * Restaurant page to view a specific page given the id
     * of the restaurant
     * @param model to get variables to the page
     * @param id the id of the restaurant
     * @return html page the html template page
     */
    @RequestMapping("restaurant/{id}")
    public String element(Model model, @PathVariable int id){
        model.addAttribute("specificRest", service.findById(id));

        return "element";
    }

    /**
     * Activity page to view a specific page given the id
     * of the Activity
     * @param model to get variables to the page
     * @param id the id of the activity
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
     * @param model to get variables to the page
     * @return html page the html template page
     */
    @RequestMapping("/random")
    public String random(Model model){
        Random random = new Random();
        if(random.nextInt(2) > 0){
            model.addAttribute("specificRest", service.random());
            return "element";
        }
        model.addAttribute("specificRest", activityService.random());
        return "elementActivity";
    }

    /**
     * Add A restaurant to the summary. Just the Form page
     * @param model to get variables to the page
     * @return html page the html template page
     */
    @GetMapping("restaurant/addRestaurant")
    public String loadForm(Model model){
        model.addAttribute("title", "Add a New Restaurant!");
        model.addAttribute("restaurant", new Restaurant());
        model.addAttribute("redirectTo", "/bored/restaurant/addRestaurant");
        return "add-restaurant";
    }

    /**
     * Add a activity to the summary. Just the Form Page
     * @param model to get variables to the page
     * @return html page the html template page
     */
    @GetMapping("activity/addActivity")
    public String loadFormActivity(Model model){
        model.addAttribute("title", "Add a New Activity!");
        model.addAttribute("activity", new Activity());
        model.addAttribute("redirectTo", "/bored/activity/addActivity");
        return "add-activity";
    }

    /**
     * Saves the new restaurant to the repo
     * @param res The restaurant details
     * @return html page the html template page
     */
    @PostMapping("restaurant/addRestaurant")
    public String handleForm(@ModelAttribute Restaurant res){
        System.out.println("Posted from form " + res);

        service.save(res);
        return "redirect:/bored/restaurants/summary";
    }

    /**
     * Saves the new activity to the repo
     * @param activity the activity details
     * @return html page the html template page
     */
    @PostMapping("activity/addActivity")
    public String handleFormActivity(@ModelAttribute Activity activity){
        System.out.println("Posted from form " + activity);

        activityService.save(activity);
        return "redirect:/bored/activities/summary";
    }

    /**
     * Updates/edits the restaurant. displays the form page
     *
     * @param model to get variables to the page
     * @param id the if of the restaurant
     * @return html page the html template page
     */
    @GetMapping("restaurant/editRestaurant/{id}")
    public String editForm(Model model, @PathVariable int id){
        model.addAttribute("title", "Update Restaurant");
        model.addAttribute("restaurant", service.findById(id));
        model.addAttribute("redirectTo", "/bored/restaurant/editRestaurant/" + id);

        return "add-restaurant";
    }

    /**
     * Updates/edits the activity. displays the from page
     *
     * @param model to get variables to the page
     * @param id the if of the activity
     * @return html page the html template page
     */
    @GetMapping("activity/editActivity/{id}")
    public String editFormActivity(Model model, @PathVariable int id){
        model.addAttribute("title", "Update Activity");
        model.addAttribute("activity", activityService.findById(id));
        model.addAttribute("redirectTo", "/bored/activity/editActivity/" + id);

        return "add-activity";
    }


    /**
     * Saves the edited restaurant to the repo
     *
     * @param res the restaurant with the details
     * @return html page
     */
    @PostMapping("restaurant/editRestaurant/{id}")
    public String handleEditForm(@ModelAttribute Restaurant res){
        System.out.println("Posted from form RESTAURANT" + res);

        service.save(res);
        return "redirect:/bored/restaurants/summary";
    }

    /**
     * Saves the edited activity to the repo
     *
     * @param activity the restaurant with the details
     * @return html page
     */
    @PostMapping("activity/editActivity/{id}")
    public String handleEditFormActivity(@ModelAttribute Activity activity){
        System.out.println("Posted from form ACTIVITY" + activity);

        activityService.save(activity);
        return "redirect:/bored/activities/summary";
    }

    /**
     * deletes the restaurant given the id
     *
     * @param id the id of the restaurant
     * @return the restaurant summary page
     */
    @RequestMapping("restaurant/deleteRestaurant/{id}")
    public String deleteRestaurant(@PathVariable int id){
        service.deleteByID(id);
        return "redirect:/bored/restaurants/summary";
    }

    /**
     * deletes the activity given the id
     *
     * @param id the id of the activity
     * @return the activity summary page
     */
    @RequestMapping("activity/deleteActivity/{id}")
    public String deleteActivity(@PathVariable int id){
        activityService.deleteByID(id);
        return "redirect:/bored/activities/summary";
    }


    /**
     * all restaurants in the database using the api
     * @return html page
     */
    @GetMapping("restaurants")
    public String getWebApiRestaurants(){
        return "restaurants";
    }

    /**
     * all activities in the database using api
     * @return html page
     */
    @GetMapping("activities")
    public String getWebApiActivities(){
        return "activities";
    }

    /**
     * @return the admin page to display all metrics of the running app
     */
    @GetMapping("admin")
    public String getAdmin(){
        return "admin";
    }

    /**
     * Add A User. Just the Form page
     * @param model to get variables to the page
     * @return html page the html template page
     */
    @GetMapping("register")
    public String addUserForm(Model model){
        User user = new User();
        model.addAttribute("user", user);

        return "register";
    }

    /**
     * Added User to database
     *
     * @param user
     * @return
     */
    @PostMapping("register")
    public String handleForm(@ModelAttribute User user){
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(new Authority(0, "user", user));

        user.setPermissions(authorityList);

        loginService.save(user);
        return "redirect:/bored";
    }

}
