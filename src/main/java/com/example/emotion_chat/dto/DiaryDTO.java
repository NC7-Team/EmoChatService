package com.example.emotion_chat.dto;

import com.example.emotion_chat.entity.Diary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DiaryDTO {

  private int diaryId;
  private int Id;
  private Diary.Emotion emotion;
  private String content;
  private LocalDate createdDate;
}
