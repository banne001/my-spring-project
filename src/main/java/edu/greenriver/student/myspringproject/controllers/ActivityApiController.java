package edu.greenriver.student.myspringproject.controllers;

import edu.greenriver.student.myspringproject.models.Activity;
import edu.greenriver.student.myspringproject.models.Restaurant;
import edu.greenriver.student.myspringproject.services.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activities")
@CrossOrigin(origins="http://localhost:8080")
public class ActivityApiController {
    private ActivityService service;

    public ActivityApiController(ActivityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Activity>> allRestaurants(){
        return new ResponseEntity<>(service.allActivity(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Activity> restaurantByID(@PathVariable int id){
        if(!service.activityExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Activity> addRestaurant(@RequestBody Activity activity){
        return new ResponseEntity<>(service.save(activity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Activity> editRestaurant(@RequestBody Activity activity){
        if(!service.activityExists(activity.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.editActivity(activity), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Activity> deleteRestaurant(@PathVariable int id){

        if(!service.activityExists(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        service.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
