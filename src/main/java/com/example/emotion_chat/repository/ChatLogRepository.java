package com.example.emotion_chat.repository;

import com.example.emotion_chat.entity.ChatLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {

  @Query("SELECT c.dateEntered, c.emotion FROM ChatLog c WHERE c.user.id = :id")
  List<Object[]> findEmotionsByUserId(@Param("id") Long id);

  @Query("SELECT c FROM ChatLog c WHERE c.user.id = :id ORDER BY c.dateEntered DESC")
  List<ChatLog> findLatestEmotion(@Param("id") Long userId, Pageable pageable);

}
