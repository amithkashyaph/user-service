package com.ecommerce.userservice.service.interfaces;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.entity.SessionStatus;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<UserDto> login(String email, String password);

    ResponseEntity<Void> logout(String token, Long userId);

    ResponseEntity<UserDto> signup(String email, String password);

    SessionStatus validate(String token, Long userId);
}
