package com.example.emotion_chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class RoomSocketController {

  @MessageMapping("/roomCreated")
  @SendTo("/subscription/roomCreated")
  public String roomCreated() {
    // 이 메서드는 방 생성 이벤트를 WebSocket 토픽으로 발행
    return "Room Created";
  }
}
