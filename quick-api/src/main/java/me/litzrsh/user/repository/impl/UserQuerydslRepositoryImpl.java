package me.litzrsh.user.repository.impl;

import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.transaction.Transactional;
import me.litzrsh.user.models.QUser;
import me.litzrsh.user.models.User;
import me.litzrsh.user.repository.UserQuerydslRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Objects;
import java.util.Optional;

public class UserQuerydslRepositoryImpl extends QuerydslRepositorySupport implements UserQuerydslRepository {

    public UserQuerydslRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> findUserByLoginId(String loginId) {
        QUser user = QUser.user;
        return Optional.ofNullable(Objects.requireNonNull(getQuerydsl()).createQuery()
                .select(user)
                .from(user)
                .where(user.loginId.equalsIgnoreCase(loginId))
                .fetchFirst());
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        QUser user = QUser.user;
        return Optional.ofNullable(Objects.requireNonNull(getQuerydsl()).createQuery()
                .select(user)
                .from(user)
                .where(user.email.equalsIgnoreCase(email))
                .fetchFirst());
    }

    @Override
    public Optional<User> findUserByMobile(String mobile) {
        QUser user = QUser.user;
        return Optional.ofNullable(Objects.requireNonNull(getQuerydsl()).createQuery()
                .select(user)
                .from(user)
                .where(user.mobile.eq(mobile))
                .fetchFirst());
    }
}
