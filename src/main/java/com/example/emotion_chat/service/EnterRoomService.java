package kr.megaptera.chatting.services;

import kr.megaptera.chatting.dto.MessageResponseDto;
import kr.megaptera.chatting.utils.MessageIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnterRoomService {

    private final SimpMessagingTemplate template;

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
    }
}
