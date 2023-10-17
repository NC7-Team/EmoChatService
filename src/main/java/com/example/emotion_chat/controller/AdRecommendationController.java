package com.example.emotion_chat.controller;

import com.example.emotion_chat.entity.AdRecommendation;
import com.example.emotion_chat.service.AdRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/adrecommendations")
public class AdRecommendationController {

  @Autowired
  private AdRecommendationService adRecommendationService;

  @GetMapping("/recommend/{userId}")
  public List<AdRecommendation> recommendAdForUser(@PathVariable Long userId) {
    return adRecommendationService.recommendAd(userId);
  }
}
