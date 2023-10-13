package com.example.emotion_chat.dto;

import com.example.emotion_chat.entity.ChatLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ChatLogDTO {

  private Long chatlogId;
  private Long userId;
  private ChatLog.Emotion emotion;
  private LocalDate dateEntered;

}
