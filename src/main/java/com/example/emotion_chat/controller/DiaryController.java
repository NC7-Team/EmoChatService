package com.example.emotion_chat.controller;

import com.example.emotion_chat.entity.Diary;
import com.example.emotion_chat.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {
  @Autowired
  private DiaryService diaryService;

  @GetMapping("/{diaryId}")
  public ResponseEntity<Diary> getDiary(@PathVariable Long diaryId) {
    Diary diary = diaryService.getDiaryById(diaryId);
    if (diary != null) {
      return new ResponseEntity<>(diary, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/create")
  public ResponseEntity<Diary> createDiary(@RequestBody Diary diary) {
    Diary createdDiary = diaryService.createDiary(diary);
    return new ResponseEntity<>(createdDiary, HttpStatus.CREATED);
  }

  @PutMapping("/{diaryId}")
  public ResponseEntity<Diary> updateDiary(@PathVariable Long diaryId, @RequestBody Diary diary) {
    Diary updatedDiary = diaryService.updateDiary(diaryId, diary);
    if (updatedDiary != null) {
      return new ResponseEntity<>(updatedDiary, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{diaryId}")
  public ResponseEntity<Void> deleteDiary(@PathVariable Long diaryId) {
    boolean deleted = diaryService.deleteDiary(diaryId);
    if (deleted) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
