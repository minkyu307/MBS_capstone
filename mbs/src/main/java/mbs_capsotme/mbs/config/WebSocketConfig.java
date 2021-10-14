package mbs_capsotme.mbs.config;

import lombok.RequiredArgsConstructor;
import mbs_capsotme.mbs.handler.ChatHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatHandler chatHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "ws/chat").setAllowedOrigins("*");
    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {

    }
}
