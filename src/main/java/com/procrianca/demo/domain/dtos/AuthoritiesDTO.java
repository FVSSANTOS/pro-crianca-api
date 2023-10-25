package com.procrianca.demo.domain.dtos;

import com.procrianca.demo.config.Authority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class AuthoritiesDTO {

    private List<Authority> authorities;

    public AuthoritiesDTO(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public static AuthoritiesDTO fromUserDetails(UserDetails userDetails) {
        List<Authority> authorities = userDetails.getAuthorities()
                .stream()
                .map(authority -> new Authority(authority.getAuthority()))
                .collect(Collectors.toList());

        return new AuthoritiesDTO(authorities);
    }

}
