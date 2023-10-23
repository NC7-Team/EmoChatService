package com.example.emotion_chat.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

@Service
@Getter @Setter
public class ChatRoomService {
    private final Map<Long, Set<Long>> chatRooms = new HashMap<>();


    //채팅방 입장시 유저 추가
    public void addUserToChatRoom(Long roomId, Long userId) {
        chatRooms.computeIfAbsent(roomId, k -> new HashSet<>()).add(userId);
        System.out.println(chatRooms);
    }

    //채팅방 퇴장시 유저 제거
    public void removeUserToChatRoom(Long roomId, Long userId) {
        chatRooms.computeIfAbsent(roomId, k -> new HashSet<>()).remove(userId);
        System.out.println(chatRooms);
    }

    public Set<Long> getUsersInChatRoom(Long roomId) {
        return chatRooms.getOrDefault(roomId, new HashSet<>());
    }

}
