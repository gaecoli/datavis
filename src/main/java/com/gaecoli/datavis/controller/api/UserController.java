package com.gaecoli.datavis.controller.api;

import com.gaecoli.datavis.controller.base.BaseController;
import com.gaecoli.datavis.entity.User;
import com.gaecoli.datavis.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @GetMapping(value = "/get")
    public User getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

}
