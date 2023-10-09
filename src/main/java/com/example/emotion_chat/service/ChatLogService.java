package com.example.emotion_chat.service;

import com.example.emotion_chat.entity.ChatLog;
import com.example.emotion_chat.repository.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatLogService {

  @Autowired
  private ChatLogRepository chatLogRepository;

  public List<ChatLog> getAllChatLogs() {
    return chatLogRepository.findAll();
  }

//  public List<ChatLogDTO> getChatLogsByDate(Long userId, LocalDate date) {
//    User user = new User(userId);  // Assuming the user exists
//    List<ChatLog> chatLogs = chatLogRepository.findByUserAndDateEntered(user, date);
//
//    return chatLogs.stream()
//            .map(this::convertToDTO)
//            .collect(Collectors.toList());
//  }

  public ChatLog saveChatLog(ChatLog chatLog) {
    return chatLogRepository.save(chatLog);
  }

}
