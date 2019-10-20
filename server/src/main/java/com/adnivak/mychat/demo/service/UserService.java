package com.adnivak.mychat.demo.service;

import com.adnivak.mychat.demo.exception.AppException;
import com.adnivak.mychat.demo.model.User;
import com.adnivak.mychat.demo.repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User setUserOnline(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.get();
        return this.setUserOnlineStatus(user, true);
    }
    public User setUserOffline(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.get();
        return this.setUserOnlineStatus(user, false);
    }

    private User setUserOnlineStatus(User user, boolean status) {
        user.setOnline(status);
        userRepository.save(user);
        return user;
    }

    public User getUserByUsername(String username) {
        Optional<User> userOptional = this.userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new AppException("User not found");
        }
        return userOptional.get();
    }
}
