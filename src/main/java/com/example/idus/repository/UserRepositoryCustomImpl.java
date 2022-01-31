package com.example.idus.repository;

import com.example.idus.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.idus.domain.QOrder.order;
import static com.example.idus.domain.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public PageImpl<User> findAllByCondition(String name, String email, Pageable pageable) {
        List<User> content = query.selectFrom(user)
                .leftJoin(user.orderList, order).fetchJoin()
                .where(usernameEq(name), teamNameEq(email))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(user.createdDate.desc())
                .fetch();

        long total = query.select(user.count())
                .from(user)
                .where(usernameEq(name), teamNameEq(email))
                .fetchOne();
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression usernameEq(String name) {
        return StringUtils.hasText(name) ? user.name.eq(name) : null;
    }

    private BooleanExpression teamNameEq(String email) {
        return StringUtils.hasText(email) ? user.email.eq(email) : null;
    }
}