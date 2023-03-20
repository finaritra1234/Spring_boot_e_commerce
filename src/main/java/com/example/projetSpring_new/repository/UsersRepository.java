package com.example.projetSpring_new.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetSpring_new.model.Users;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {}