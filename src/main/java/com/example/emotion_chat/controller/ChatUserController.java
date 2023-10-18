package com.example.emotion_chat.controller;

import com.example.emotion_chat.dto.ChatRoomsResponseDto;
import com.example.emotion_chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequestMapping("/chatUsers")
@RequiredArgsConstructor
public class ChatUserController {
    private final ChatRoomService chatRoomService;

    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoomsResponseDto> getUsersInChatRoom(@PathVariable Long roomId) {
        Set<Long> usersInChatRoom = chatRoomService.getUsersInChatRoom(roomId);
        ChatRoomsResponseDto responseDto = new ChatRoomsResponseDto(chatRoomService.getChatRooms(), usersInChatRoom);
        return ResponseEntity.ok(responseDto);
    }
//@GetMapping("/{roomId}")
//public ResponseEntity<Set<Long>> getUsersInChatRoom(@PathVariable Long roomId) {
//    Set<Long> usersInChatRoom = chatRoomService.getUsersInChatRoom(roomId);
//    return ResponseEntity.ok(usersInChatRoom);
//}
}
