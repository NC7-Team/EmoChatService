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
@Table(name = "SpecialDates")
public class SpecialDate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long specialDateId;

  private LocalDate date;

  private String description;
}
