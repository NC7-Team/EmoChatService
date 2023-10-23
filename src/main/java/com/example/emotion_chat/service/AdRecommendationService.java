package com.example.emotion_chat.service;

import com.example.emotion_chat.entity.AdRecommendation;
import com.example.emotion_chat.entity.ChatLog;
import com.example.emotion_chat.repository.AdRecommendationRepository;
import com.example.emotion_chat.repository.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdRecommendationService {

  @Autowired
  private ChatLogRepository chatLogRepository;

  @Autowired
  private AdRecommendationRepository adRecommendationRepository;


  public List<AdRecommendation> recommendAd(Long userId) {

    // 최근 감정 채팅 로그 가져오기
    List<ChatLog> recentChatLogs = chatLogRepository.findLatestEmotion(userId, PageRequest.of(0, 7));

    // 콘솔에 recentChatLogs 출력
    System.out.println("Recent Chat Logs: " + recentChatLogs);

    // 로그 비었는지 확인
    if (recentChatLogs.isEmpty()) {
      return getDefaultAd();
    }

    // 각 감정의 빈도 계산
    Map<ChatLog.Emotion, Long> emotionFrequency = recentChatLogs.stream()
            .collect(Collectors.groupingBy(ChatLog::getEmotion, Collectors.counting()));

    // 지배적인 감정 확인
    ChatLog.Emotion dominantEmotion = Collections.max(emotionFrequency.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey();

    // 동일한 최대 빈도의 감정이 여러 개 있는 경우, 최근의 감정을 dominantEmotion으로 설정
    if (Collections.frequency(emotionFrequency.values(), emotionFrequency.get(dominantEmotion)) > 1) {
      dominantEmotion = recentChatLogs.get(0).getEmotion();  // 최근 감정 가져오기
    }

    // 연속적인 감정 확인
    if (isContinuousRecentEmotion(recentChatLogs)) {
      dominantEmotion = recentChatLogs.get(0).getEmotion();
      return getAdsBasedOnEmotion(dominantEmotion);
    }

    // 지배적인 감정 기반 광고 추천
    List<AdRecommendation> ads = adRecommendationRepository.findAdsByEmotionOrderByRandom(dominantEmotion, PageRequest.of(0, 2));
    if (ads.size() < 2) { // 만약 감정에 기반한 광고가 2개 미만이면 추가 광고 2개로 채우기
      ads.addAll(getDefaultAd().subList(0, 2 - ads.size()));
    }

    return ads;
  }

  private List<AdRecommendation> getDefaultAd() {
    return adRecommendationRepository.findRandomAds(PageRequest.of(0, 2));
  }

  private boolean isContinuousRecentEmotion(List<ChatLog> chatLogs) {
    if(chatLogs.size() < 3) return false;  // 로그의 크기가 3 미만인 경우 연속성 확인을 스킵
    ChatLog.Emotion lastEmotion = chatLogs.get(0).getEmotion();
    return chatLogs.subList(0, 3).stream().allMatch(log -> log.getEmotion() == lastEmotion);
  }

  private List<AdRecommendation> getAdsBasedOnEmotion(ChatLog.Emotion emotion) {
    List<AdRecommendation> ads = adRecommendationRepository.findAdsByEmotionOrderByRandom(emotion, PageRequest.of(0, 2));
    if (ads.size() < 2) { // 만약 감정에 기반한 광고가 2개 미만이면 추가 광고 2개로 채우기
      ads.addAll(getDefaultAd().subList(0, 2 - ads.size()));
    }
    return ads;
  }
}
