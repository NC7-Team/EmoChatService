package com.example.emotion_chat.service;

import com.example.emotion_chat.entity.ChatLog;
import com.example.emotion_chat.entity.Diary;
import com.example.emotion_chat.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {
  @Autowired
  private DiaryRepository diaryRepository;

  public List<Diary> getAllDiary() {
    return diaryRepository.findAll();
  }

  public Diary getDiaryByChatlog(Long chatlogId) {
    return diaryRepository.findByChatlog_ChatlogId(chatlogId);
  }

  public Diary createDiary(Diary diary) {
    return diaryRepository.save(diary);
  }

//  public Diary updateDiary(Long diaryId, Diary updatedDiary) {
//    if (diaryRepository.existsById(diaryId)) {
//      updatedDiary.setChatlogId(diaryId);
//      return diaryRepository.save(updatedDiary);
//    }
//    return null;
//  }

  public boolean deleteDiary(Long diaryId) {
    if (diaryRepository.existsById(diaryId)) {
      diaryRepository.deleteById(diaryId);
      return true;
    }
    return false;
  }
}
