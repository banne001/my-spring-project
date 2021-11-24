package edu.greenriver.student.myspringproject.services;

import edu.greenriver.student.myspringproject.dbs.ActivityRepository;
import edu.greenriver.student.myspringproject.models.Activity;
import edu.greenriver.student.myspringproject.models.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

    public ActivityService(ActivityRepository repo) {
        this.repo = repo;
    }

    public List<Activity> allActivity(){
        return repo.findAll();
    }

    public Activity findById(int id){
        return repo.findById(id);
    }

    /**
     * Gets the top three rated restaurants
     * @return top three restaurants
     */
    public List<Activity> topThree(){
        List<Activity> all = repo.findAll();
        List<Activity> top3 = all.stream()
                .sorted()
                .limit(3)
                .collect(Collectors.toList());
        return top3;
    }

    /**
     * randomly picks a restaurant
     *
     * @return a random restaurant
     */
    public Activity random(){
        List<Activity> all = repo.findAll();
        Random rand = new Random();
        Activity res = all.get(rand.nextInt(all.size()));

        return res;
    }

    public boolean activityExists(int id){
        return repo.existsById(id);
    }

    public Activity save(Activity activity){
        if(repo.existsById(activity.getId())){
            throw new NoSuchElementException("Missing Product");
        }
        return repo.save(activity);
    }
    public Activity editActivity(Activity activity){
        return repo.save(activity);
    }

    public void deleteByID(int id){
        repo.deleteById(id);
    }
}
