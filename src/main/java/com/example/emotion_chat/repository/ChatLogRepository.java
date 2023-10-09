package com.example.emotion_chat.repository;

import com.example.emotion_chat.entity.ChatLog;
import com.example.emotion_chat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {
  List<ChatLog> findByUserAndDateEntered(User user, LocalDate date);
}
