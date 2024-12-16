package com.gaecoli.datavis.controller.api;

import com.gaecoli.datavis.controller.base.BaseController;
import com.gaecoli.datavis.dto.UserRegisterDTO;
import com.gaecoli.datavis.entity.User;
import com.gaecoli.datavis.service.UserService;
import com.gaecoli.datavis.utils.ResponseUtils.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @GetMapping(value = "/get")
    public User getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/register")
    public Response registerUser(@Valid @RequestBody UserRegisterDTO user) {
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
