package edu.greenriver.student.myspringproject.dbs;

import edu.greenriver.student.myspringproject.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * repository to be able to make queries, add, save, delete
 *
 * @author blezyl
 * @version 10.29
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Restaurant findById(int id);
    Restaurant deleteById(int id);
}
