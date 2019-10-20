package com.adnivak.mychat.demo.task;

import com.adnivak.mychat.demo.model.Subscription;
import com.adnivak.mychat.demo.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.List;
import java.util.Map;

@Component
public class UserSubscribeEventBroadcastTask implements ApplicationListener<SessionDisconnectEvent> {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        System.out.println("Disconnect ::: " + event.getUser().getName() + " :: " + event );
//
//        Subscription subscription = Subscription.builder()
//                .username(event.getUser().getName())
//                .build();
//
//        ApiResponse<Subscription> response = ApiResponse.ok(subscription, "sub");
//
//        Map nativeHeaders = (Map<String, Object>)event.getMessage().getHeaders().get("nativeHeaders");
//        List<String> destinations = (List<String>) nativeHeaders.get("destination");
//        System.out.println(event.getUser());
//
//        destinations.forEach(destination -> this.messagingTemplate.convertAndSend(destination, response));
    }
}
