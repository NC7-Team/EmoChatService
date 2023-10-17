package com.example.emotion_chat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "AdRecommendations")
public class AdRecommendation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long adId;

  @Enumerated(EnumType.STRING)
  private ChatLog.Emotion emotion;

  private String category;  // 광고의 카테고리 (예: 음식점, 여행, 쇼핑 등)

  private String content;  // 광고의 실제 내용 (예: "떡볶이 할인 쿠폰", "한식 뷔페 할인", 등)
}
