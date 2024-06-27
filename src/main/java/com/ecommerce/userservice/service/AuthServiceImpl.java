package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.entity.Session;
import com.ecommerce.userservice.entity.SessionStatus;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.SessionRepository;
import com.ecommerce.userservice.repository.UserRespository;
import com.ecommerce.userservice.service.interfaces.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.util.*;

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
        Optional<User> optionalUser = userRespository.findByEmail(email);

        if(optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return null;
        }

//        String token = RandomStringUtils.randomAlphanumeric(30);

        MacAlgorithm alg = Jwts.SIG.HS512; //or HS384 or HS256
        SecretKey key = alg.key().build();

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("email", user.getEmail());
        claimsMap.put("roles", user.getRoles());
        claimsMap.put("createdAt", new Date());
        claimsMap.put("expiryAt", DateUtils.addDays(new Date(), 30));

        String jwtToken = Jwts.builder()
                .claims(claimsMap)
                .signWith(key, alg)
                .compact();

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setUser(user);
        session.setToken(jwtToken);
        sessionRepository.save(session);

        UserDto userDto = new UserDto();
        userDto.setEmail(email);


        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + jwtToken);

        ResponseEntity<UserDto> response = new ResponseEntity<>(userDto, headers, HttpStatus.OK);

        return response;
    }

    @Override
    public ResponseEntity<Void> logout(String token, Long userId) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token, userId);

        if(optionalSession.isEmpty()) {
            return null;
        }

        Session session = optionalSession.get();

        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
        return null;
    }

    @Override
    public UserDto signup(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRespository.save(user);

        return UserDto.from(savedUser);
    }

    @Override
    public SessionStatus validate(String token, Long userId) {
        return null;
    }
}
