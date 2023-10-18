package com.example.emotion_chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Getter @Setter
public class ChatRoomsResponseDto {
    private Map<Long, Set<Long>> chatRooms;
    private Set<Long> usersInChatRoom;
}
