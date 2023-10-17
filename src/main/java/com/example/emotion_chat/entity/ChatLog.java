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

  public Emotion getEmotion() {
    return emotion;
  }

  public enum Emotion {
    HAPPY, SAD, ANGRY, NEUTRAL, ALL;
  }

  @Override
  public String toString() {
    return "ChatLog{" +
            "chatlogId=" + chatlogId +
            ", user=" + user +
            ", emotion=" + emotion +
            ", dateEntered=" + dateEntered +
            '}';
  }
}