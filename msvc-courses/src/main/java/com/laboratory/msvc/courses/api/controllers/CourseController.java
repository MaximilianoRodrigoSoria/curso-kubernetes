package com.laboratory.msvc.courses.api.controllers;

import com.laboratory.msvc.courses.api.model.request.CourseRequest;
import com.laboratory.msvc.courses.api.model.responses.ErrorsResponse;
import com.laboratory.msvc.courses.api.model.responses.CourseResponse;
import com.laboratory.msvc.courses.infraestructure.sevices.CoursesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/courses")
@AllArgsConstructor
@Tag(name = "Courses")
public class CourseController {

    private CoursesService coursesService;


    @ApiResponse(
            responseCode = "400",
            description = "When the request have a field invalid we response this",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Save in system a user")
    @PostMapping
    public ResponseEntity<CourseResponse> post(@Valid @RequestBody CourseRequest request) {
        return ResponseEntity.ok(coursesService.create(request));
    }

    @Operation(summary = "Return a user")
    @GetMapping(path = "{id}")
    public ResponseEntity<CourseResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(coursesService.findById(id));
    }

    @Operation(summary = "Update user")
    @PutMapping(path = "{id}")
    public ResponseEntity<CourseResponse> put(@Valid @PathVariable Long id, @RequestBody CourseRequest request) {
        return ResponseEntity.ok(coursesService.update(request, id));
    }

    @Operation(summary = "Delete a User")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.coursesService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Return a list with courses")
    @GetMapping()
    public ResponseEntity<Set<CourseResponse>> getUser(){
        var response = coursesService.findAll();
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }
}
