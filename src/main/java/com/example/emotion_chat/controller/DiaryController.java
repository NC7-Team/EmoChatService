//package com.example.emotion_chat.controller;
//
//import com.example.emotion_chat.entity.Diary;
//import com.example.emotion_chat.service.DiaryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/diary")
//public class DiaryController {
//  @Autowired
//  private DiaryService diaryService;
//
//  @PostMapping
//  public Diary createDiary(@RequestBody Diary diary) {
//    return diaryService.createDiary(diary);
//  }
//
//  @GetMapping("/user/{userId}")
//  public List<Diary> getUserDiaries(@PathVariable Long userId) {
//    return diaryService.getUserDiaries(userId);
//  }
//
//  @GetMapping("/{id}")
//  public Diary getDiaryById(@PathVariable Long id) {
//    return diaryService.getDiaryById(id);
//  }
//
//  @PutMapping("/{id}")
//  public Diary updateDiary(@PathVariable Long id, @RequestBody Diary updatedDiary) {
//    return diaryService.updateDiary(id, updatedDiary);
//  }
//
//  @DeleteMapping("/{id}")
//  public void deleteDiary(@PathVariable Long id) {
//    diaryService.deleteDiary(id);
//  }
//}
