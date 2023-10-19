package com.example.emotion_chat.service;

import com.example.emotion_chat.dto.MessageResponseDto;
import com.example.emotion_chat.util.MessageIdGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class ConvertAndSendMessageService {

    private final SimpMessagingTemplate template;

    private final String[] first = {"화끈한", "배고픈", "귀여운", "인생이 힘든", "행복한", "이상한", "짜증난", "졸린", "지겨운", "집에 가고 싶은"};


  public String filteringMessage(String message) {
    try {
      ArrayList<String> yorks = new ArrayList<>();
      ObjectMapper objectMapper = new ObjectMapper();
      ClassPathResource resource = new ClassPathResource("/badwords_ko.json");
      JsonNode jsonNode = objectMapper.readTree(resource.getFile());

      if (jsonNode.isArray()) {
        for (JsonNode element : jsonNode) {
          yorks.add(element.asText());
        }
      }

      for (String york : yorks) {
        if (message.contains(york)) {
          StringBuilder blur = new StringBuilder();
          for (int i = 0; i < york.length(); i++) {
            blur.append("*");
          }
          message = message.replaceAll(york, blur.toString());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return message;
  }

  public void convertAndSendMessage(String type, Long roomId, Long userId, String message) {


    String messageType = type.equals("message") ? "message" : "enter";

      message = filteringMessage(message);
      String randomName = RandomNameGenerator.randomName(userId.intValue());



        String messageValue = String.format(first[userId.intValue()/100] + " "+ randomName + "%s: %s", userId, message);

        template.convertAndSend(
                "/subscription/chat/room/" + roomId,
                new MessageResponseDto(
                        MessageIdGenerator.generateId(),
                        messageType,
                        messageValue
                )
        );
    }

}

