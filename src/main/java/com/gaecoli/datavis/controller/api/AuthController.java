package com.gaecoli.datavis.controller.api;

import com.gaecoli.datavis.controller.base.BaseController;
import com.gaecoli.datavis.dto.UserRegisterDTO;
import com.gaecoli.datavis.entity.User;
import com.gaecoli.datavis.service.UserService;
import com.gaecoli.datavis.utils.ResponseUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    @Resource
    UserService userService;

    @PostMapping(value = "/register")
    public ResponseUtils.Response registerUser(@Valid @RequestBody UserRegisterDTO user) {
        String errorMsg = null;
        try {
            User registerUser = userService.registerUser(user);
            if (registerUser.getIsActive()) {
                return Success("Register user success!");
            }
        } catch (RuntimeException e) {
            errorMsg = e.getMessage();
        }

        return Error("Register user error. " + errorMsg);
    }
}
