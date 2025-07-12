package com.priyanshu.dtos.userDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationRequestDTO {

    private String name;
    private String email;
    private String password;
}
