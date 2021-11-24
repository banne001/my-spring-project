package edu.greenriver.student.myspringproject.controllers;

import edu.greenriver.student.myspringproject.models.Restaurant;
import edu.greenriver.student.myspringproject.services.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurants")
@CrossOrigin(origins="http://localhost:8080")
public class RestaurantApiController {
    private RestaurantService service;

    public RestaurantApiController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> allRestaurants(){
        return new ResponseEntity<>(service.allRestaurants(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> restaurantByID(@PathVariable int id){
        if(!service.restaurantExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant){
        return new ResponseEntity<>(service.save(restaurant), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Restaurant> editRestaurant(@RequestBody Restaurant restaurant){
        if(!service.restaurantExists(restaurant.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.editRestaurant(restaurant), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable int id){

        if(!service.restaurantExists(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        service.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
