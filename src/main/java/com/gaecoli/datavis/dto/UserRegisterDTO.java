package com.gaecoli.datavis.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserRegisterDTO {

    @NotBlank
    @Size(min = 4, max = 30)
    private String name;

    @NotBlank
    @Email(message = "Invalid email address")
    @Size(min = 6, max = 50)
    private String email;

    @NotBlank
    @Size(min = 6, max = 30)
    private String password;

    @NotBlank
    @Size(min = 6, max = 30)
    private String confirmPassword;

    private Date createdAt;

    private Date updatedAt;

    private Boolean isActive;
}
