package com.example.emotion_chat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ChatLog")
public class ChatLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long chatlogId;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  private Emotion emotion;

  private LocalDate dateEntered;

  public enum Emotion {
    HAPPY, SAD, ANGRY, NEUTRAL;
  }

}
