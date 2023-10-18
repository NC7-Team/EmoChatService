package com.example.emotion_chat.service;

import com.example.emotion_chat.dto.MessageResponseDto;
import com.example.emotion_chat.util.MessageIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuitRoomService {

    private final SimpMessagingTemplate template;
    private final ChatRoomService chatRoomService;


    public void quitRoom(String type,
                         Long roomId,
                         Long userId) {

        chatRoomService.removeUserToChatRoom(roomId, userId);

        template.convertAndSend(
            "/subscription/chat/room/" + roomId,
            new MessageResponseDto(
                MessageIdGenerator.generateId(),
                type,
                "사용자 " + userId + " 님이 "
                    + "채팅방 " + roomId + "에서 나갔습니다."
            )
        );
    }
}
