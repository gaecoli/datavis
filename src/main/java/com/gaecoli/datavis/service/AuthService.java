package com.gaecoli.datavis.service;

import com.gaecoli.datavis.dto.JwtUser;
import com.gaecoli.datavis.dto.UserLoginDTO;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    JwtUser login(UserLoginDTO userLogin);

    void logout();
}
