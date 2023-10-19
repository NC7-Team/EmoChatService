package com.example.emotion_chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaceDTO {
  private String gender;
  private double genderConfidence;
  private String age;
  private double ageConfidence;
  private String emotion;
  private double emotionConfidence;
}
