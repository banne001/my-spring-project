package edu.greenriver.student.myspringproject.dbs;

import edu.greenriver.student.myspringproject.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
