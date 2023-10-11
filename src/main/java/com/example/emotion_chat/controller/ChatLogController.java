package com.example.emotion_chat.controller;

import com.example.emotion_chat.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/chatlogs")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatLogController {

  @Autowired
  private ChatLogService chatLogService;

  @GetMapping("/emotions/{id}")
  public ResponseEntity<Map<String, String>> getEmotionsByUserId(@PathVariable Long id) {
    Map<String, String> emotions = chatLogService.getEmotionsByUserId(id);
    return ResponseEntity.ok(emotions);
  }
}
