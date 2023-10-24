package com.example.emotion_chat.service;

import com.example.emotion_chat.dto.MessageResponseDto;
import com.example.emotion_chat.util.MessageIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EnterRoomService {
    private final SimpMessagingTemplate template;
    private final ChatRoomService chatRoomService;

    public void enterRoom(String type,
                          Long roomId,
                          Long userId) {

        chatRoomService.addUserToChatRoom(roomId, userId);
        String room;
        if(roomId == 1L) {
            room = "화남";
        } else if (roomId == 2L) {
            room = "슬픔";
        } else if (roomId == 3L){
            room = "기쁨";
        } else {
            room = "스파링";
        }

        template.convertAndSend(
            "/subscription/chat/room/" + roomId,
            new MessageResponseDto(
                MessageIdGenerator.generateId(),
                type,
                "사용자 " + userId + " 님이 "
                    + room +"채팅방 " + roomId + "에 입장하셨습니다."
            )
        );

    }

}
