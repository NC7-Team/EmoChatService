package com.example.emotion_chat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "diary")
public class Diary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long diaryId;

  @OneToOne
  @JoinColumn(name = "chatlog_id", nullable = false)
  private ChatLog chatlog;

  @Lob
  private String content;
}
