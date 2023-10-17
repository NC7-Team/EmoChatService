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
@Table(name = "SpecialDates")
public class SpecialDate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long specialDateId;

  private LocalDate date;

  private String description;
}
