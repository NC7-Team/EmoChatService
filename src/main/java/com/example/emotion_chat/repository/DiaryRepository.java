package com.example.emotion_chat.repository;

import com.example.emotion_chat.entity.Diary;
import com.example.emotion_chat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
