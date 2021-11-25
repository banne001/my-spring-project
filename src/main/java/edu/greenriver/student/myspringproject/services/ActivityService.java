package edu.greenriver.student.myspringproject.services;

import edu.greenriver.student.myspringproject.dbs.ActivityRepository;
import edu.greenriver.student.myspringproject.models.Activity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Restaurant Service class to access the restaurant data
 * and manipulate it for the use that the user needs it for
 *
 * @author blezyl
 * @version 10.12
 */

@Service
public class ActivityService {

    private ActivityRepository repo;
    /**
     * Constructor
     *
     * @param repo declares the repo
     */
    public ActivityService(ActivityRepository repo) {
        this.repo = repo;
    }
    /**
     * All the Activity
     * @return List of Activity
     */
    public List<Activity> allActivity(){
        return repo.findAll();
    }
    /**
     * gets the Activity that matches id
     *
     * @param id the id of the Activity
     * @return the Activity with the same name as the name param
     */
    public Activity findById(int id){
        return repo.findById(id);
    }

    /**
     * Gets the top three rated Activity
     * @return top three Activity
     */
    public List<Activity> topThree(){
        List<Activity> all = repo.findAll();
        return all.stream()
                .sorted()
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * randomly picks a Activity
     *
     * @return a random Activity
     */
    public Activity random(){
        List<Activity> all = repo.findAll();
        Random rand = new Random();

        return all.get(rand.nextInt(all.size()));
    }
    /**
     * Checks if the given id exists
     *
     * @param id the id of the restaurant
     * @return boolean true if it exists, otherwise false
     */
    public boolean activityExists(int id){
        return repo.existsById(id);
    }
    /**
     * Saves the activity to the repo
     *
     * @param activity Saves the activity object to the repo
     * @return the activity that is saved
     */
    public Activity save(Activity activity){

        return repo.save(activity);
    }
    /**
     * Save the updated activity
     *
     * @param activity the activity to be updated
     * @return the updated activity
     */
    public Activity editActivity(Activity activity){
        if(!repo.existsById(activity.getId())){
            throw new NoSuchElementException("Missing Product");
        }
        return repo.save(activity);
    }
    /**
     * Delete the given Id
     *
     * @param id to be deleted
     */
    public void deleteByID(int id){
        repo.deleteById(id);
    }
}
