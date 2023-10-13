package com.example.emotion_chat;

import com.example.emotion_chat.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class EmotionChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmotionChatApplication.class, args);
	}
}
