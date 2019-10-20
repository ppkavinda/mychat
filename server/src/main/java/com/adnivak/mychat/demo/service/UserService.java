package com.adnivak.mychat.demo.service;

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
}
