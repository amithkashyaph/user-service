package com.ecommerce.userservice.security;

import com.ecommerce.userservice.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize(as = CustomeGrantedAuthority.class)
public class CustomeGrantedAuthority implements GrantedAuthority {

    private Role role;

    public CustomeGrantedAuthority(Role role) {
        this.role = role;
    }
    @Override
    @JsonIgnore
    public String getAuthority() {
        return role.getRole();
    }
}
