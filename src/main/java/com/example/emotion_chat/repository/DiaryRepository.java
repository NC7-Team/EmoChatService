package com.example.emotion_chat.repository;

import com.example.emotion_chat.entity.ChatLog;
import com.example.emotion_chat.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

  Diary findByChatlog_ChatlogId(long chatlogId);
}
