package com.example.emotion_chat.service;

import com.example.emotion_chat.entity.ChatLog;
import com.example.emotion_chat.repository.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatLogService {

  @Autowired
  private ChatLogRepository chatLogRepository;

  public Map<String, String> getEmotionsByUserId(Long userId) {
    List<Object[]> results = chatLogRepository.findEmotionsByUserId(userId);
    Map<String, String> emotionsMap = new HashMap<>();

    for(Object[] result : results) {
      LocalDate date = (LocalDate) result[0];
      ChatLog.Emotion emotionEnum = (ChatLog.Emotion) result[1];
      emotionsMap.put(date.toString(), emotionEnum.name());
    }

    return emotionsMap;
  }
}
