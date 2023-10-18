package com.example.emotion_chat.controller;

import com.example.emotion_chat.dto.MessageRequestDto;
import com.example.emotion_chat.service.ConvertAndSendMessageService;
import com.example.emotion_chat.service.EnterRoomService;
import com.example.emotion_chat.service.QuitRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MessageController {
    private final EnterRoomService enterRoomService;
    private final QuitRoomService quitRoomService;
    private final ConvertAndSendMessageService convertAndSendMessageService;


    @MessageMapping("/chat/enter")
    public void enter(MessageRequestDto messageRequestDto) {
        enterRoomService.enterRoom(
            messageRequestDto.getType(),
            messageRequestDto.getRoomId(),
            messageRequestDto.getUserId()
        );

    }

    @MessageMapping("/chat/quit")
    public void quit(MessageRequestDto messageRequestDto) {
        quitRoomService.quitRoom(
            messageRequestDto.getType(),
            messageRequestDto.getRoomId(),
            messageRequestDto.getUserId()
        );
    }

    @MessageMapping("/chat/message")
    public void message(MessageRequestDto messageRequestDto) {
        convertAndSendMessageService.convertAndSendMessage(
            messageRequestDto.getType(),
            messageRequestDto.getRoomId(),
            messageRequestDto.getUserId(),
            messageRequestDto.getMessage()
        );
    }

@MessageExceptionHandler(Exception.class)
@SendToUser("/queue/errors")
public String handleException(Exception exception, SimpMessageHeaderAccessor headerAccessor) {
    // 예외 처리 로직을 여기에 추가
    System.out.println(exception.getMessage());
    return "An exception occurred: " + exception.getMessage();
}
}
