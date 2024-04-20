package com.vn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProfileDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String description;
}
