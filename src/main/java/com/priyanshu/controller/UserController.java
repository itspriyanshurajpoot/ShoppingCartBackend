package com.priyanshu.controller;


import com.priyanshu.dtos.userDTO.RegistrationRequestDTO;
import com.priyanshu.dtos.userDTO.UserResponseDTO;
import com.priyanshu.response.ApiResponse;
import com.priyanshu.service.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User Controller")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    // Register User
    @Operation(summary = "Register User")
    @PostMapping
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegistrationRequestDTO dto){

        ApiResponse response = ApiResponse.builder()
                .data(userService.registerUser(dto))
                .message("Registration successful")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id){
        ApiResponse response = ApiResponse.builder()
                .success(true)
                .message("Fetched successful")
                .data(userService.getUserById(id))
                .build();

        return ResponseEntity.ok(response);
    }
}
