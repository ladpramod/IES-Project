package com.ies.module.admin.api.security;

import com.ies.module.admin.api.entities.User;
import com.ies.module.admin.api.exception.ApiException;
import com.ies.module.admin.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(username).orElseThrow(()-> new ApiException("user email not found: " +username));
        return user;

    }
}
