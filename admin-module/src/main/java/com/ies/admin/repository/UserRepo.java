package com.ies.admin.repository;

import com.ies.admin.bindings.UserDto;
import com.ies.admin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

    public UserEntity findByEmail(String email);
}
