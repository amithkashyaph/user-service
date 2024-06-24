package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.entity.SessionStatus;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.SessionRepository;
import com.ecommerce.userservice.repository.UserRespository;
import com.ecommerce.userservice.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRespository userRespository;

    private SessionRepository sessionRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthServiceImpl(UserRespository userRespository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRespository = userRespository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public ResponseEntity<UserDto> login(String email, String password) {
        return null;
    }

    @Override
    public ResponseEntity<Void> logout(String token, Long userId) {
        return null;
    }

    @Override
    public UserDto signup(String email, String password) {
        User user = new User();
        return null;
    }

    @Override
    public SessionStatus validate(String token, Long userId) {
        return null;
    }
}
