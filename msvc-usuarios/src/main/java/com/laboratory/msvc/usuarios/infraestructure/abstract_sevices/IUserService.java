package com.laboratory.msvc.usuarios.infraestructure.abstract_sevices;

import com.laboratory.msvc.usuarios.api.model.request.UserRequest;
import com.laboratory.msvc.usuarios.api.model.responses.UserResponse;

import java.util.Set;

public interface IUserService extends CrudService<UserRequest, UserResponse, Long> {
    Set<UserResponse> findAll();
}
