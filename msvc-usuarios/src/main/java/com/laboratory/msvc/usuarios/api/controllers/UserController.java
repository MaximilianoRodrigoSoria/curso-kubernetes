package com.laboratory.msvc.usuarios.api.controllers;

import com.laboratory.msvc.usuarios.api.model.request.UserRequest;
import com.laboratory.msvc.usuarios.api.model.responses.UserResponse;
import com.laboratory.msvc.usuarios.infraestructure.sevices.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users")
@AllArgsConstructor
@Tag(name = "Users")
public class UserController {

    private UsersService usersService;


    @ApiResponse(
            responseCode = "400",
            description = "When the request have a field invalid we response this",
            content = {
                    //@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Save in system a user")
    @PostMapping
    public ResponseEntity<UserResponse> post(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(usersService.create(request));
    }

    @Operation(summary = "Return a user")
    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.findById(id));
    }

    @Operation(summary = "Update user")
    @PutMapping(path = "{id}")
    public ResponseEntity<UserResponse> put(@Valid @PathVariable Long id, @RequestBody UserRequest request) {
        return ResponseEntity.ok(usersService.update(request, id));
    }

    @Operation(summary = "Delete a User")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.usersService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
