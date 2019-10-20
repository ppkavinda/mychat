package com.adnivak.mychat.demo.task;

import com.adnivak.mychat.demo.payload.ApiResponse;
import com.adnivak.mychat.demo.payload.dto.UserDTO;
import com.adnivak.mychat.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class SessionConnectedEventBroadcastTask implements ApplicationListener<SessionConnectedEvent> {
    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        if (event.getUser() != null) {
            String username = event.getUser().getName();
            userService.setUserOnline(username);
            UserDTO userDTO = UserDTO.builder().username(username).online(true).build();
            ApiResponse<UserDTO> response = ApiResponse.ok(userDTO, "onlineStatus");

            messagingTemplate.convertAndSend("/chat/public", response);

            System.out.println("online: " + response.getData());
        }
    }
}
