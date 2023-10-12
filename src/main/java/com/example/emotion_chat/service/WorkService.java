package com.example.emotion_chat.service;

import com.example.emotion_chat.entity.Work;
import com.example.emotion_chat.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkService {
  private final WorkRepository workRepository;

  @Autowired
  public WorkService(WorkRepository workRepository) {
    this.workRepository = workRepository;
  }

  public List<Work> getAllWorks() {
    return workRepository.findAll();
  }

  // 다른 데이터베이스 작업 메서드도 추가 가능
}
