package com.example.idus.repository;

import com.example.idus.domain.User;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryCustom {

    PageImpl<User> findAllByCondition(String name, String email, Pageable pageable);

}
