package com.example.emotion_chat.dto;


import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class RoomCreateRequest{
  private String roomTitle;
  private int roomIndex;
  private String roomName;
  private static final int MAX_CAPACITY = 2;

  public RoomCreateRequest(int roomIndex, String roomTitle) {
    this.roomIndex = roomIndex;
    this.roomTitle = roomTitle;
    this.roomName = ""; // 또는 다른 기본값 설정
  }
}
