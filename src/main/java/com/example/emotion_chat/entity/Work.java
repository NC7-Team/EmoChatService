package com.example.emotion_chat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Work {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer work_no;
  private String work_name;
  private String work_type;
  private String recommend_gender;
  private String recommend_age;
  private String recommend_emotion;
  private Integer view_count;

}
