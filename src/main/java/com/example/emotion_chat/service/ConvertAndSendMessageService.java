package com.example.emotion_chat.service;

import com.example.emotion_chat.dto.MessageResponseDto;
import com.example.emotion_chat.util.MessageIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class ConvertAndSendMessageService {
    private final SimpMessagingTemplate template;
    private final List<String> names;


    public void convertAndSendMessage(String type, Long roomId, Long userId, String message) {
        String messageType = type.equals("message") ? "message" : "enter";

        String[] first = new String[1];
        first[0] = "화끈한";

        String randomName = RandomNameGenerator.randomName(userId.intValue());

        // Include user information in the message
        String messageValue = String.format(first[0]+ randomName + "%s: %s", userId, message);

        template.convertAndSend(
                "/subscription/chat/room/" + roomId,
                new MessageResponseDto(
                        MessageIdGenerator.generateId(),
                        messageType,
                        messageValue
                )
        );
    }

}

