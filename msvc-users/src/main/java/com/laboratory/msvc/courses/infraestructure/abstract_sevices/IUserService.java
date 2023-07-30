package com.laboratory.msvc.courses.infraestructure.abstract_sevices;

import com.laboratory.msvc.courses.api.model.request.UserRequest;
import com.laboratory.msvc.courses.api.model.responses.UserResponse;

import java.util.Set;

public interface IUserService extends CrudService<UserRequest, UserResponse, Long> {
    Set<UserResponse> findAll();
}
