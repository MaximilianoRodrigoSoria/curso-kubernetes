package com.laboratory.msvc.courses.infraestructure.sevices;

import com.laboratory.msvc.courses.api.model.request.CourseRequest;
import com.laboratory.msvc.courses.api.model.responses.CourseResponse;
import com.laboratory.msvc.courses.domain.entities.jpa.Course;
import com.laboratory.msvc.courses.infraestructure.abstract_sevices.ICoursesService;
import com.laboratory.msvc.courses.repositories.CoursesRepository;
import com.laboratory.msvc.courses.util.enums.Tables;
import com.laboratory.msvc.courses.util.exceptions.CourseNotFoundException;
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
public class CoursesService implements ICoursesService {

    private CoursesRepository coursesRepository;




    @Override
    public Set<CourseResponse> findAll() {
        return coursesRepository.findAll()
                .stream()
                .map(v-> entityToResponse.apply(v))
                .collect(Collectors.toSet());
    }

    @Override
    public CourseResponse findById(Long id) {
        return  Stream.of(coursesRepository.findById(id).orElseThrow(()-> new CourseNotFoundException(id,Tables.courses.name())))
                .map(entityToResponse::apply)
                .findFirst()
                .get();
    }

    @Override
    public CourseResponse create(CourseRequest request) {
        return Stream.of(request)
                .map(requestToEntity::apply)
                .map(coursesRepository::save)
                .map(entityToResponse::apply)
                .findFirst()
                .get();

    }

    @Override
    public CourseResponse update(CourseRequest request, Long id) {
        var user = coursesRepository.findById(id).orElseThrow(()-> new CourseNotFoundException(id, Tables.courses.name()));
        return Stream.of(user)
                .map(u-> updateEntity.apply(request,u))
                .map(u -> coursesRepository.save(u))
                .map(entityToResponse::apply)
                .findFirst()
                .get();
    }



    @Override
    public void delete(Long id) {
        coursesRepository.findById(id).orElseThrow(()-> new CourseNotFoundException(id,Tables.courses.name()));
        var user = coursesRepository.findById(id).orElseThrow();
        coursesRepository.delete(user);

    }


    static  Function<Course, CourseResponse> entityToResponse = (n) -> {
        var response = new CourseResponse();
        BeanUtils.copyProperties(n, response);
        return response;
    };


    static Function<CourseRequest, Course> requestToEntity = (n) -> {
        var c = new Course();
        BeanUtils.copyProperties(n, c);
        return c;
    };

    static BiFunction<CourseRequest, Course, Course> updateEntity = (courseRequest, user) -> {
        BeanUtils.copyProperties(courseRequest, user);
        return user;
    };


}
