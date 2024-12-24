package com.gaecoli.datavis.dto;

import com.gaecoli.datavis.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    private String name;

    private String email;

    private Date createdAt;

    private Date updatedAt;

    private Boolean isActive;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.isActive = user.getIsActive();
    }
}
