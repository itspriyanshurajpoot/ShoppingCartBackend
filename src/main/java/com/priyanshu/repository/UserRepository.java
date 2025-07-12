package com.priyanshu.repository;

import com.priyanshu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailIgnoreCase(String email);
}
