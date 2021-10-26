package edu.greenriver.student.myspringproject.dbs;

import edu.greenriver.student.myspringproject.models.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

}
