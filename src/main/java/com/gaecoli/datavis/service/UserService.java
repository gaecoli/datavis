package com.gaecoli.datavis.service;

import com.gaecoli.datavis.dto.UserDTO;
import com.gaecoli.datavis.dto.UserRegisterDTO;
import org.springframework.stereotype.Service;
import com.gaecoli.datavis.entity.User;

@Service
public interface UserService {
    UserDTO getUserById(Long id);

    User getUserByEmail(String email);

    User registerUser(UserRegisterDTO dto);

    void updateUser(User user);
}
