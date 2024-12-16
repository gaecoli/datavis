package com.gaecoli.datavis.service.impl;

import com.gaecoli.datavis.dto.UserDTO;
import com.gaecoli.datavis.dto.UserRegisterDTO;
import com.gaecoli.datavis.entity.User;
import com.gaecoli.datavis.mapper.UserMapper;
import com.gaecoli.datavis.service.UserService;
import com.gaecoli.datavis.utils.EncryptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDTO getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return null;
        }
        // 转为 UserDTO， 不能透出密码字段
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User registerUser(UserRegisterDTO dto) {
        User findUser = userMapper.getUserByEmail(dto.getEmail());
        if (Optional.ofNullable(findUser).isPresent()) {
            throw new RuntimeException("User already exists!");
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new RuntimeException("Password and confirm password do not match!");
        }

        Date now = new Date();

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setIsActive(true);
        user.setPassword(EncryptionUtils.encryptPassword(dto.getPassword()));
        userMapper.insert(user);

        return user;
    }


}
