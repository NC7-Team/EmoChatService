package com.example.emotion_chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

/**
 * 채팅방 유저목록을위한 DTO
 */
@AllArgsConstructor
@Getter @Setter
public class ChatRoomsResponseDto {
    private Map<Long, Set<Long>> chatRooms;
    private Set<Long> usersInChatRoom;
}
