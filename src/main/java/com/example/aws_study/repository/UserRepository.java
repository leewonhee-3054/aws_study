package com.example.aws_study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aws_study.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}