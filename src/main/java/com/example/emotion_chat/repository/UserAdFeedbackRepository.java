package com.example.emotion_chat.repository;

import com.example.emotion_chat.entity.UserAdFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface UserAdFeedbackRepository extends JpaRepository<UserAdFeedback, Long> {

  // 사용자가 클릭한 광고들 중에서 가장 많이 클릭된 광고의 카테고리를 찾는 쿼리
  @Query("SELECT ad.category FROM UserAdFeedback feedback " +
          "JOIN feedback.adRecommendation ad " +
          "WHERE feedback.user.id = :userId AND feedback.clicked = true " +
          "GROUP BY ad.category " +
          "ORDER BY COUNT(feedback) DESC")
  List<String> findMostClickedCategoriesByUserId(@Param("userId") Long userId, Pageable pageable);
}
