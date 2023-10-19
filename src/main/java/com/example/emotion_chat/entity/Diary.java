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
@Table(name = "diary")
public class Diary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long chatlogId;

  @OneToOne
  @JoinColumn(name = "chatlog_id")
  private ChatLog chatLog;

  @Lob
  private String content;

  public enum Emotion {
    HAPPY, NEUTRAL, ANGRY, SAD;
  }
}
