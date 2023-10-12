package kr.megaptera.chatting.services;

import kr.megaptera.chatting.dto.MessageResponseDto;
import kr.megaptera.chatting.utils.MessageIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertAndSendMessageService {
    private final SimpMessagingTemplate template;

    public void convertAndSendMessage(String type,
                                      Long roomId,
                                      Long userId,
                                      String message) {
        template.convertAndSend(
            "/subscription/chat/room/" + roomId,
            new MessageResponseDto(
                MessageIdGenerator.generateId(),
                type,
                "사용자 " + userId + ": " + message
            )
        );
    }
}
