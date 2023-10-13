package kr.megaptera.chatting.services;

import kr.megaptera.chatting.dto.MessageResponseDto;
import kr.megaptera.chatting.utils.MessageIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuitRoomService {

    private final SimpMessagingTemplate template;

    public void quitRoom(String type,
                         Long roomId,
                         Long userId) {
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
