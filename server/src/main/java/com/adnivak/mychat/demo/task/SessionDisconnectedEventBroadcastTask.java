package com.adnivak.mychat.demo.task;

import com.adnivak.mychat.demo.model.User;
import com.adnivak.mychat.demo.payload.ApiResponse;
import com.adnivak.mychat.demo.payload.dto.UserDTO;
import com.adnivak.mychat.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class SessionDisconnectedEventBroadcastTask implements ApplicationListener<SessionDisconnectEvent> {
    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        if (event.getUser() != null) {
            String username = event.getUser().getName();
            userService.setUserOnline(username);

            UserDTO userDTO = UserDTO.builder().username(username).build();

            ApiResponse<UserDTO> response = ApiResponse.ok(userDTO, "online");

            messagingTemplate.convertAndSend("/chat/public", response);
            System.out.println("offline: " + response.getData());
        }

    }
}
