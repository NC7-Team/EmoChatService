package com.example.emotion_chat.controller;

import com.example.emotion_chat.entity.Work;
import com.example.emotion_chat.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/works")
public class WorkController {
  private final WorkService workService;

  @Autowired
  public WorkController(WorkService workService) {
    this.workService = workService;
  }

  @GetMapping
  public List<Work> getAllWorks() {
    return workService.getAllWorks();
  }
}
