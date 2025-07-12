package com.priyanshu.mappers;

import com.priyanshu.dtos.userDTO.RegistrationRequestDTO;
import com.priyanshu.dtos.userDTO.UserResponseDTO;
import com.priyanshu.entity.User;

public class UserMapper {

    public static UserResponseDTO toResponseDto(User user){
        return UserResponseDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .id(user.getId())
                .build();
    }


    public static User toEntity(RegistrationRequestDTO dto){
        return User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
    }
}
