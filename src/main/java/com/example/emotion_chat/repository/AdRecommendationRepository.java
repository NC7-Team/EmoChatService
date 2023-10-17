package com.example.emotion_chat.repository;

import com.example.emotion_chat.entity.AdRecommendation;
import com.example.emotion_chat.entity.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface AdRecommendationRepository extends JpaRepository<AdRecommendation, Long> {

  // 사용자 감정에 맞는 광고를 조회
  @Query("SELECT a FROM AdRecommendation a WHERE a.emotion = ?1 ORDER BY RAND()")
  List<AdRecommendation> findAdsByEmotionOrderByRandom(ChatLog.Emotion emotion, Pageable pageable);

  // 모든 광고 중에서 무작위 순서로 광고 조회
  @Query("SELECT a FROM AdRecommendation a ORDER BY RAND()")
  List<AdRecommendation> findRandomAds(Pageable pageable);

}