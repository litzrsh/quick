package me.litzrsh.authority.repository;

import me.litzrsh.authority.models.Authority;

import java.util.List;

public interface AuthorityQuerydslRepository {

    List<Authority> findAllUserAuthoritiesForSession(String userId);
}
