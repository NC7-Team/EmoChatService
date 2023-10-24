//package com.example.emotion_chat.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@Table(name = "diary")
//public class Diary {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long diaryId;
//
//  @ManyToOne
//  @JoinColumn(name = "id")
//  private User user;
//
//  @Column(nullable = false)
//  private LocalDate createdDate;
//
//  @Column(nullable = false)
//  private String emotion;
//
//  @Lob
//  private String content;
//
//  public enum Emotion {
//    HAPPY, NEUTRAL, ANGRY, SAD;
//  }
//}
