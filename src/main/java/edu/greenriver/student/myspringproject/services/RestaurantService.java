package edu.greenriver.student.myspringproject.services;

import edu.greenriver.student.myspringproject.dbs.RestaurantRepository;
import edu.greenriver.student.myspringproject.models.Restaurant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class RestaurantService {

    private RestaurantRepository repo;

    public RestaurantService(RestaurantRepository repo) {
        this.repo = repo;
    }

    public List<Restaurant> allRestaurants(){
        return repo.findAll();
    }

    /**
     * gets the restaurant that matches id
     *
     * @param id the id of the restaurant
     * @return the restaurant with the same name as the name param
     */
    public Restaurant findById(int id){
        return repo.findById(id);
    }

    /**
     * Gets the top three rated restaurants
     * @return top three restaurants
     */
    public List<Restaurant> topThree(){
        List<Restaurant> all = repo.findAll();
        List<Restaurant> top3 = all.stream()
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
    public Restaurant random(){
        List<Restaurant> all = repo.findAll();
        Random rand = new Random();
        Restaurant res = all.get(rand.nextInt(all.size()));

        return res;
    }

    public boolean restaurantExists(int id){
        return repo.existsById(id);
    }

    /**
     *
     * @param res Saves the Restaurant object to the repo
     */
    public Restaurant save(Restaurant res){

        return repo.save(res);
    }

    public Restaurant editRestaurant(Restaurant restaurant){
        if(!repo.existsById(restaurant.getId())){
            throw new NoSuchElementException("Missing Product");
        }
        return repo.save(restaurant);
    }

    public void deleteByID(int id){
        repo.deleteById(id);
    }
}

