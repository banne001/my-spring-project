package edu.greenriver.student.myspringproject.dbs;

import edu.greenriver.student.myspringproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 User Repository to be able to add and view users
 @author Blezyl Santos
 @version 12/3/2021
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * @param username username to fine user by
     * @return User correlated to the username
     */
    User findByUsername(String username);
}
