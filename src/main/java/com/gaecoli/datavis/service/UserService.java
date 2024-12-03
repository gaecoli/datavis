package com.gaecoli.datavis.service;

import com.gaecoli.datavis.dto.UserRegisterDTO;
import org.springframework.stereotype.Service;
import com.gaecoli.datavis.entity.User;

@Service
public interface UserService {
    User getUserById(Long id);

    User getUserByEmail(String email);

    User registerUser(UserRegisterDTO dto);
}
