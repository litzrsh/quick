package me.litzrsh.user.repository;

import me.litzrsh.user.models.User;

import java.util.Optional;

public interface UserQuerydslRepository {

    Optional<User> findUserByLoginId(String loginId);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByMobile(String mobile);
}
