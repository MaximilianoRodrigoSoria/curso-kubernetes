package com.laboratory.msvc.courses.infraestructure.abstract_sevices;

import com.laboratory.msvc.courses.api.model.request.CourseRequest;
import com.laboratory.msvc.courses.api.model.responses.CourseResponse;

import java.util.Set;

public interface ICoursesService extends CrudService<CourseRequest, CourseResponse, Long> {
    Set<CourseResponse> findAll();
}
