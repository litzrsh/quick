package me.litzrsh.user.repository;

import me.litzrsh.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends UserQuerydslRepository, JpaRepository<User, String> {
}
