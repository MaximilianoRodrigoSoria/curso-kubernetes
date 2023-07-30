package com.laboratory.msvc.courses.infraestructure.sevices;

import com.laboratory.msvc.courses.api.model.request.UserRequest;
import com.laboratory.msvc.courses.api.model.responses.UserResponse;
import com.laboratory.msvc.courses.domain.entities.jpa.User;
import com.laboratory.msvc.courses.infraestructure.abstract_sevices.IUserService;
import com.laboratory.msvc.courses.repositories.UserRepository;
import com.laboratory.msvc.courses.util.enums.Tables;
import com.laboratory.msvc.courses.util.exceptions.UsernameNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UsersService implements IUserService {

    private UserRepository userRepository;




    @Override
    public Set<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(v-> entityToResponse.apply(v))
                .collect(Collectors.toSet());
    }

    @Override
    public UserResponse findById(Long id) {
        return  Stream.of(userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException(id,Tables.user.name())))
                .map(entityToResponse::apply)
                .findFirst()
                .get();
    }

    @Override
    public UserResponse create(UserRequest request) {
        return Stream.of(request)
                .map(requestToEntity::apply)
                .map(userRepository::save)
                .map(entityToResponse::apply)
                .findFirst()
                .get();

    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        var user = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException(id, Tables.user.name()));
        return Stream.of(user)
                .map(u-> updateEntity.apply(request,u))
                .map(u -> userRepository.save(u))
                .map(entityToResponse::apply)
                .findFirst()
                .get();
    }



    @Override
    public void delete(Long id) {
        userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException(id,Tables.user.name()));
        var user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);

    }


    static  Function<User,UserResponse> entityToResponse = (n) -> {
        var response = new UserResponse();
        BeanUtils.copyProperties(n, response);
        return response;
    };


    static Function<UserRequest,User> requestToEntity = (n) -> {
        var user = new User();
        BeanUtils.copyProperties(n, user);
        return user;
    };

    static BiFunction<UserRequest,User,User> updateEntity = (userRequest, user) -> {
        BeanUtils.copyProperties(userRequest, user);
        return user;
    };


}
