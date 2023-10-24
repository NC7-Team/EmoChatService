//package com.example.emotion_chat.service;
//
//import com.example.emotion_chat.entity.Diary;
//import com.example.emotion_chat.entity.User;
//import com.example.emotion_chat.repository.DiaryRepository;
//import com.example.emotion_chat.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class DiaryService {
//  @Autowired
//  private DiaryRepository diaryRepository;
//  @Autowired
//  private UserRepository userRepository;
//
//  public Diary createDiary(Diary diary) {
//    return diaryRepository.save(diary);
//  }
//
//  public List<Diary> getUserDiaries(Long userId) {
//    User user = userRepository.findById(userId).orElse(null);
//    if (user != null) {
//      return diaryRepository.findByUser(user);
//    }
//    return Collections.emptyList();
//  }
//
//  public Diary getDiaryById(Long id) {
//    return diaryRepository.findById(id).orElse(null);
//  }
//
//  public Diary updateDiary(Long id, Diary updatedDiary) {
//    Diary diary = diaryRepository.findById(id).orElse(null);
//    if (diary != null) {
//      diary.setEmotion(updatedDiary.getEmotion());
//      diary.setContent(updatedDiary.getContent());
//      return diaryRepository.save(diary);
//    }
//    return null;
//  }
//
//  public void deleteDiary(Long id) {
//    diaryRepository.deleteById(id);
//  }
//}
