package me.litzrsh.authority.repository.impl;

import me.litzrsh.authority.models.Authority;
import me.litzrsh.authority.models.QAuthority;
import me.litzrsh.authority.repository.AuthorityQuerydslRepository;
import me.litzrsh.user.models.QUserAuthority;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;

public class AuthorityQuerydslRepositoryImpl extends QuerydslRepositorySupport implements AuthorityQuerydslRepository {

    public AuthorityQuerydslRepositoryImpl() {
        super(Authority.class);
    }

    @Override
    public List<Authority> findAllUserAuthoritiesForSession(String userId) {
        QAuthority authority = QAuthority.authority;
        QUserAuthority userAuthority = QUserAuthority.userAuthority;

        return Objects.requireNonNull(getQuerydsl()).createQuery()
                .select(authority)
                .from(authority)
                .innerJoin(userAuthority).on(
                        userAuthority.userId.eq(userId)
                                .and(userAuthority.authorityId.eq(authority.id))
                )
                .where(authority.use.isTrue())
                .fetch();
    }
}
