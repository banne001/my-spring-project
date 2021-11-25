package edu.greenriver.student.myspringproject.controllers;

import edu.greenriver.student.myspringproject.models.Activity;
import edu.greenriver.student.myspringproject.services.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Activity Service class to access the activity data using Restful Api Service
 *
 * @author blezyl
 * @version 11/24
 */
@RestController
@RequestMapping("activities")
@CrossOrigin(origins="http://localhost:8080")
public class ActivityApiController {
    private ActivityService service;

    /**
     * Constructor
     * @param service the activity service
     */
    public ActivityApiController(ActivityService service) {
        this.service = service;
    }

    /**
     * List of all the activities
     *
     * @return List all activities
     */
    @GetMapping
    public ResponseEntity<List<Activity>> allActivity(){
        return new ResponseEntity<>(service.allActivity(), HttpStatus.OK);
    }

    /**
     * get all the details of an activity given the id
     *
     * @param id The id of the activity
     * @return the Activity details given the id
     */
    @GetMapping("{id}")
    public ResponseEntity<Activity> activityByID(@PathVariable int id){
        if(!service.activityExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
    }

    /**
     * Adds a new Activity to the service
     *
     * @param activity a new Activity
     * @return the newly added activity
     */
    @PostMapping
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity){
        return new ResponseEntity<>(service.save(activity), HttpStatus.CREATED);
    }

    /**
     * Edits/updates the activity
     *
     * @param activity edits the activity
     * @return the newly edited activity
     */
    @PutMapping
    public ResponseEntity<Activity> editActivity(@RequestBody Activity activity){
        if(!service.activityExists(activity.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.editActivity(activity), HttpStatus.CREATED);
    }

    /**
     * Deletes the Activity given the id
     *
     * @param id to be deleted
     * @return Http Status
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable int id){

        if(!service.activityExists(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        service.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
