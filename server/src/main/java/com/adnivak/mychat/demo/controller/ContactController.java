package com.adnivak.mychat.demo.controller;

import com.adnivak.mychat.demo.model.Message;
import com.adnivak.mychat.demo.model.User;
import com.adnivak.mychat.demo.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ContactController {
    @Autowired
    SimpUserRegistry simpUserRegistry;

    @GetMapping(value = "/contacts/getall")
    public ApiResponse<List<User>> getAllContacts() {
        List<User> responseList = new ArrayList<>();

        Set<SimpUser> users = simpUserRegistry.getUsers();
        users.forEach(user -> {
            User u = new User();
            u.setName(user.getName());
        });

        return ApiResponse.ok(responseList, "contacts");
    }
}
