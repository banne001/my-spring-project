package edu.greenriver.student.myspringproject.services;

import edu.greenriver.student.myspringproject.dbs.RestaurantRepository;
import edu.greenriver.student.myspringproject.models.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Restaurant Service class to access the restaurant data
 *
 * @author blezyl
 * @version 11/24
 */
@Service
public class RestaurantService {

    private RestaurantRepository repo;

    /**
     * Constructor
     *
     * @param repo declares the repo
     */
    public RestaurantService(RestaurantRepository repo) {
        this.repo = repo;
    }

    /**
     * All the Restaurants
     * @return List of Restaurants
     */
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
        return all.stream()
                .sorted()
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * randomly picks a restaurant
     *
     * @return a random restaurant
     */
    public Restaurant random(){
        List<Restaurant> all = repo.findAll();
        Random rand = new Random();

        return all.get(rand.nextInt(all.size()));
    }

    /**
     * Checks if the given id exists
     *
     * @param id the id of the restaurant
     * @return boolean true if it exists, otherwise false
     */
    public boolean restaurantExists(int id){
        return repo.existsById(id);
    }

    /**
     * Saves the Restaurant to the repo
     *
     * @param res Saves the Restaurant object to the repo
     * @return the restaurant that is saved
     */
    public Restaurant save(Restaurant res){
        return repo.save(res);
    }

    /**
     * Save the updated Restaurant
     *
     * @param restaurant the restaurant to be updated
     * @return the updated restaurant
     */
    public Restaurant editRestaurant(Restaurant restaurant){
        if(!repo.existsById(restaurant.getId())){
            throw new NoSuchElementException("Missing Product");
        }
        return repo.save(restaurant);
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

