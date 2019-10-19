package com.adnivak.mychat.demo.controller;

import com.adnivak.mychat.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class ChatController {
    @Autowired
    SimpUserRegistry simpUserRegistry;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping(value = "/public")
    @SendTo("/chat/public")
    public Message send(final Message message) {
        Set<SimpUser> users = simpUserRegistry.getUsers();
        System.out.println(users + ":::" + simpUserRegistry.getUserCount());
        return message;
    }

}
