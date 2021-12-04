package edu.greenriver.student.myspringproject.dbs;

import edu.greenriver.student.myspringproject.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 Authority Repository to be make queries on what type of role a user has
 @author Blezyl Santos
 @version 12/3/2021
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
