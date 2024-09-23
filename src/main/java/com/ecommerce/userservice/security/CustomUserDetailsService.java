package com.ecommerce.userservice.security;

import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRespository userRespository;

    @Autowired
    public CustomUserDetailsService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRespository.findByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("User with given email not found");
        });
        return new CustomUserDetails(user);
    }
}
