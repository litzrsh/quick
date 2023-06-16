package me.litzrsh.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.litzrsh.authority.repository.AuthorityRepository;
import me.litzrsh.user.models.User;
import me.litzrsh.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuickUserDetailsService implements UserDetailsService, UserDetailsPasswordService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User entity = this.userRepository.findUserByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        entity.setAuthorities(this.authorityRepository
                .findAllUserAuthoritiesForSession(entity.getId()));
        log.trace("Get user details from database : {}", entity);
        return entity;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        this.userRepository.findById(user.getUsername())
                .ifPresent(entity -> {
                    entity.setPassword(newPassword);
                    this.userRepository.save(entity);
                });
        return user;
    }
}
