package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.LoginRequestDto;
import com.ecommerce.userservice.dto.LogoutRequestDto;
import com.ecommerce.userservice.dto.SignUpRequestDto;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        UserDto userDto = authService.signup(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());

        return new ResponseEntity(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }


}
