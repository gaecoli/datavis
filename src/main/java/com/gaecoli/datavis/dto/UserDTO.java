package com.gaecoli.datavis.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String name;

    private String email;

    private Long createdAt;

    private Long updatedAt;

    private Boolean isActive;
}
