package com.gaecoli.datavis.service.impl;

import com.gaecoli.datavis.dto.JwtUser;
import com.gaecoli.datavis.dto.UserDTO;
import com.gaecoli.datavis.dto.UserLoginDTO;
import com.gaecoli.datavis.entity.User;
import com.gaecoli.datavis.service.AuthService;
import com.gaecoli.datavis.service.UserService;
import com.gaecoli.datavis.utils.EncryptionUtils;
import com.gaecoli.datavis.utils.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    UserService userService;

    @Override
    public JwtUser login(UserLoginDTO userLogin) {
        String userEmail = userLogin.getEmail();
        String userPassword = userLogin.getPassword();

        User user = userService.getUserByEmail(userEmail);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + userEmail);
        }

        if (EncryptionUtils.verifyPassword(userPassword, user.getPassword())) {
            String token = JwtUtils.generateToken(user.getName());

            Authentication authentication = JwtUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());

            return new JwtUser(userDTO, token);
        }
        throw new BadCredentialsException("The user name or password error.");
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
