package com.tarun.UserInfo.service;

import com.tarun.UserInfo.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    User saveUser(User user);
    Optional<User> findByUsername(String username);
}
