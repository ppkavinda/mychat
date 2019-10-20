package com.adnivak.mychat.demo.task;

import com.adnivak.mychat.demo.model.Message;
import com.adnivak.mychat.demo.model.Subscription;
import com.adnivak.mychat.demo.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserUnSubscribeEventBroadcastTask implements ApplicationListener<SessionConnectedEvent> {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
        System.out.println(event);
        Subscription subscription = Subscription.builder()
                .username(event.getUser().getName())
                .build();

        ApiResponse<Subscription> response = ApiResponse.ok(subscription, "unsub");

        Map nativeHeaders = (Map<String, Object>)event.getMessage().getHeaders().get("nativeHeaders");
        List<String> destinations = (List<String>) nativeHeaders.get("destination");

        destinations.forEach(destination -> this.messagingTemplate.convertAndSend(destination, response));

    }

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        System.out.println("sessionConnect::: " + event.getUser().getName() + " :: " + event);
//        Subscription subscription = Subscription.builder()
//                .username(event.getUser().getName())
//                .build();
//
//        ApiResponse<Subscription> response = ApiResponse.ok(subscription, "unsub");
//
//        Map nativeHeaders = (Map<String, Object>)event.getMessage().getHeaders().get("nativeHeaders");
//        List<String> destinations = (List<String>) nativeHeaders.get("destination");
//
//        destinations.forEach(destination -> this.messagingTemplate.convertAndSend(destination, response));

    }
}
