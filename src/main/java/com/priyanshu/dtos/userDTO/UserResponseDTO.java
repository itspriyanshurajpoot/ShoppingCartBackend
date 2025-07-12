package com.priyanshu.dtos.userDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
}
