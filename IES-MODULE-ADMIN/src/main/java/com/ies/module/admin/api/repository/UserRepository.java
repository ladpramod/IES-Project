package com.ies.module.admin.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.module.admin.api.entities.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>{


    public Optional<User>  findByEmail(String email);

    public User findByEmailAndPazzd(String email, String pazzd);

}
