package com.example.emotion_chat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long chatlogId;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @Enumerated(EnumType.STRING)
  private Emotion emotion;

  private LocalDate dateEntered;

  public enum Emotion {
    HAPPY, SAD, ANGRY, NEUTRAL;
  }

}