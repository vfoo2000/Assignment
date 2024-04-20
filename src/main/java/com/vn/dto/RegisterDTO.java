package com.vn.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    @NotBlank(message = "You need to write a name!")
    private String userName;
    @NotBlank(message = "Email cannot be blank!")
    private String email;
    @NotBlank(message = "Your account need a password!")
    private String password;
    @NotBlank(message = "Please write the password one more time!")
    private String rePassword;
}
