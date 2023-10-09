package com.example.emotion_chat.controller;

import com.example.emotion_chat.entity.ChatLog;
import com.example.emotion_chat.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatLogController {

  @Autowired
  private ChatLogService chatLogService;

  @GetMapping("/chatlogs")
  public List<ChatLog> getAllChatLogs() {
    return chatLogService.getAllChatLogs();
  }

  @PostMapping("/chatlogs")
  public ChatLog saveChatLog(@RequestBody ChatLog chatLog) {
    return chatLogService.saveChatLog(chatLog);
  }

}
