package com.laboratory.msvc.courses.repositories;

import com.laboratory.msvc.courses.domain.entities.jpa.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CoursesRepository extends CrudRepository<Course,Long> {
    Set<Course> findAll();


}
