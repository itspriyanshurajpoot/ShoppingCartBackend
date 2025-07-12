package com.priyanshu.service.user;

import com.priyanshu.dtos.userDTO.RegistrationRequestDTO;
import com.priyanshu.dtos.userDTO.UserResponseDTO;
import com.priyanshu.entity.User;
import com.priyanshu.mappers.UserMapper;
import com.priyanshu.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO registerUser(RegistrationRequestDTO dto) {
        // Check the email already registered or not
        User existedUser = userRepository.findByEmailIgnoreCase(dto.getEmail());

        if(existedUser != null){
            throw new RuntimeException("Email already registered");
        }


        // convert dto into entity
        User user = UserMapper.toEntity(dto);
        user.setRole("USER");


        // save the user
        User savedUser = userRepository.save(user);


        // convert entity into response dto and return it
        return UserMapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + id));
    }
}
