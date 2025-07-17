package com.thymeleaf3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thymeleaf3.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
