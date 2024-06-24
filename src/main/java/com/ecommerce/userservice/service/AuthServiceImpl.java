package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.entity.SessionStatus;
import com.ecommerce.userservice.service.interfaces.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseEntity<UserDto> login(String email, String password) {
        return null;
    }

    @Override
    public ResponseEntity<Void> logout(String token, Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<UserDto> signup(String email, String password) {
        return null;
    }

    @Override
    public SessionStatus validate(String token, Long userId) {
        return null;
    }
}
