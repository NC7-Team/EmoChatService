package com.example.emotion_chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {
  private String value;
  private double confidence;
  private byte[] imageBytes;
}
