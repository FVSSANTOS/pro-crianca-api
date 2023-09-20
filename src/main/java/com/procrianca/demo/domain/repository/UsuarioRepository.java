package com.procrianca.demo.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procrianca.demo.domain.entity.User;

public interface UsuarioRepository extends JpaRepository<User, Integer>{
    Optional<User> findByLogin(String login);
}
