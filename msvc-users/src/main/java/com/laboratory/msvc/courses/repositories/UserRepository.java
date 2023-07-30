package com.laboratory.msvc.courses.repositories;

import com.laboratory.msvc.courses.domain.entities.jpa.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository<User,Long> {
    Set<User> findAll();


}
