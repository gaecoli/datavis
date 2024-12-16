package com.gaecoli.datavis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtUser {
    private UserDTO user;

    private String token;
}
