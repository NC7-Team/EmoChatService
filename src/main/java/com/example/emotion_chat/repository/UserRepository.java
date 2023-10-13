package com.example.emotion_chat.repository;

import com.example.emotion_chat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
