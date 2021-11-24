package edu.greenriver.student.myspringproject.dbs;

import edu.greenriver.student.myspringproject.models.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    Activity findById(int id);
}
