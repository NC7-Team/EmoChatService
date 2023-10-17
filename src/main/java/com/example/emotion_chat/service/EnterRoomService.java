package com.example.emotion_chat.service;

import com.example.emotion_chat.dto.MessageResponseDto;
import com.example.emotion_chat.util.MessageIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service

@RequiredArgsConstructor
public class EnterRoomService {
    private final SimpMessagingTemplate template;
    // 사용자 목록을 저장하는 Set
    private final Map<Long, Set<Long>> connectedUsersByRoom = new HashMap<>();

    public void enterRoom(String type,
                          Long roomId,
                          Long userId) {

        template.convertAndSend(
            "/subscription/chat/room/" + roomId,
            new MessageResponseDto(
                MessageIdGenerator.generateId(),
                type,
                "사용자 " + userId + " 님이 "
                    + "채팅방 " + roomId + "에 입장하셨습니다."
            )
        );

        connectedUsersByRoom
                .computeIfAbsent(roomId, k -> new HashSet<>())
                .add(userId);
    }

}
