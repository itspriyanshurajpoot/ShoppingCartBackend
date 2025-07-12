package com.priyanshu.service.user;

import com.priyanshu.dtos.userDTO.RegistrationRequestDTO;
import com.priyanshu.dtos.userDTO.UserResponseDTO;

public interface IUserService {

    // Register user
    UserResponseDTO registerUser(RegistrationRequestDTO dto);

    // Get user by ID
    UserResponseDTO getUserById(Long id);
}
