package com.laboratory.msvc.usuarios.repositories;

import com.laboratory.msvc.usuarios.domain.entities.jpa.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository<User,Long> {
    Set<User> findAll();


}
