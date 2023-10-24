package com.example.emotion_chat.controller;

import com.example.emotion_chat.entity.ChatLog;
import com.example.emotion_chat.entity.Diary;
import com.example.emotion_chat.service.ChatLogService;
import com.example.emotion_chat.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {
  @Autowired
  private DiaryService diaryService;

  @Autowired
  private ChatLogService chatLogService;

  @GetMapping("/list")
  public ResponseEntity<List<Diary>> getAllDiary() {
    List<Diary> diaries = diaryService.getAllDiary();
    return new ResponseEntity<>(diaries, HttpStatus.OK);
  }

  @GetMapping("/{userId}/{date}")
  public ResponseEntity<Diary> getDiary(@PathVariable Long userId, @PathVariable String date) {
    try {
      ChatLog chatLog = chatLogService.getEmotionsByUserIdAndDate(userId, LocalDate.parse(date, DateTimeFormatter.ISO_DATE));

      Diary temp = diaryService.getDiaryByChatlog(chatLog.getChatlogId());

      Diary diary = new Diary();
      diary.setDiaryId(temp.getDiaryId());
      diary.setContent(temp.getContent());

      return new ResponseEntity<>(diary, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/create")
  public ResponseEntity<Diary> createDiary(
          @RequestParam("userId") Long userId,
          @RequestParam("date") String date,
          @RequestParam("entry") String content) {

    ChatLog chatLog = chatLogService.getEmotionsByUserIdAndDate(userId, LocalDate.parse(date, DateTimeFormatter.ISO_DATE));

    Diary diary = diaryService.getDiaryByChatlog(chatLog.getChatlogId());

    if (diary == null) {
      diary = new Diary();
      diary.setChatlog(chatLog);
      diary.setContent(content);
    } else {
      diary.setContent(content);
    }
    Diary createdDiary = diaryService.createDiary(diary);
    return new ResponseEntity<>(createdDiary, HttpStatus.CREATED);
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
