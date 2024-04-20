package com.vn.entities;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;

    @Size(max = 50)
    private String userName;

    @Size(max = 50)
    private String password;

    @Size(max = 50)
    private String email;

    private String phone;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
