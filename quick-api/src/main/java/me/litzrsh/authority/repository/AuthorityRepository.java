package me.litzrsh.authority.repository;

import me.litzrsh.authority.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends AuthorityQuerydslRepository, JpaRepository<Authority, String> {
}
