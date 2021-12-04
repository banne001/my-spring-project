package edu.greenriver.student.myspringproject.dbs;

import edu.greenriver.student.myspringproject.models.Activity;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * repository to be able to make queries, add, save, delete
 *
 * @author blezyl
 * @version 11/24
 */
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    /**
     * @param id id of the Activity
     * @return Activity correlated to the ID
     */
    Activity findById(int id);
}
