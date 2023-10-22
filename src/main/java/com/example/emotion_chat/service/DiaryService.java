package com.example.emotion_chat.service;

import com.example.emotion_chat.entity.Diary;
import com.example.emotion_chat.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
  @Autowired
  private DiaryRepository diaryRepository;

  public Diary getDiaryById(Long diaryId) {
    return diaryRepository.findById(diaryId).orElse(null);
  }

  public Diary createDiary(Diary diary) {
    return diaryRepository.save(diary);
  }

  public Diary updateDiary(Long diaryId, Diary updatedDiary) {
    if (diaryRepository.existsById(diaryId)) {
      updatedDiary.setChatlogId(diaryId);
      return diaryRepository.save(updatedDiary);
    }
    return null;
  }

  public boolean deleteDiary(Long diaryId) {
    if (diaryRepository.existsById(diaryId)) {
      diaryRepository.deleteById(diaryId);
      return true;
    }
    return false;
  }
}
