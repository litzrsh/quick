package me.litzrsh.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface OAuth2UserDetailsService {

    UserDetails loadUserDetails(String accessToken);
}
