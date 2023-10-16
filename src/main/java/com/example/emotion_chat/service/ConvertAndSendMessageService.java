package com.example.emotion_chat.service;

import com.example.emotion_chat.dto.MessageResponseDto;
import com.example.emotion_chat.util.MessageIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertAndSendMessageService {
    private final SimpMessagingTemplate template;



//    public void convertAndSendMessage(String type,
//                                      Long roomId,
//                                      Long userId,
//                                      String message) {
//        template.convertAndSend(
//            "/subscription/chat/room/" + roomId,
//            new MessageResponseDto(
//                MessageIdGenerator.generateId(),
//                type,
//                "사용자 " + userId + ": " + message
//            )
//        );
//    }
public void convertAndSendMessage(String type,
                                  Long roomId,
                                  Long userId,
                                  String message) {
    String messageType = (type.equals("message") && userId.equals(userId)) ? "내가 보낸 Message" : "otherMessage";
    template.convertAndSend(
            "/subscription/chat/room/" + roomId,
            new MessageResponseDto(
                    MessageIdGenerator.generateId(),
                    messageType,
                    "사용자 " + userId + ":" + " " + message
            )
    );
}



}
