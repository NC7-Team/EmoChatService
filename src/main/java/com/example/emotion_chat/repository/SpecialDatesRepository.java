package com.example.emotion_chat.repository;

import com.example.emotion_chat.entity.SpecialDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SpecialDatesRepository extends JpaRepository<SpecialDate, Long> {
  boolean existsByDate(LocalDate date);
}
