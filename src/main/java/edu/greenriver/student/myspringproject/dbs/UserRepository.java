package edu.greenriver.student.myspringproject.dbs;

import edu.greenriver.student.myspringproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
