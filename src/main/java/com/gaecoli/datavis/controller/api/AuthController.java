package com.gaecoli.datavis.controller.api;

import com.gaecoli.datavis.common.constant.SecurityConstants;
import com.gaecoli.datavis.controller.base.BaseController;
import com.gaecoli.datavis.dto.JwtUser;
import com.gaecoli.datavis.dto.UserDTO;
import com.gaecoli.datavis.dto.UserLoginDTO;
import com.gaecoli.datavis.service.AuthService;
import com.gaecoli.datavis.service.UserService;
import com.gaecoli.datavis.utils.ResponseUtils.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    @Resource
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        JwtUser jwtUser = authService.login(userLoginDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + jwtUser.getToken());
        return new ResponseEntity<>(jwtUser.getUser(), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public Response logout() {
        authService.logout();
        return Success("Logout success");
    }
}
