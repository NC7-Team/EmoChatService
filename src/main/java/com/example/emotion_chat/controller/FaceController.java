package com.example.emotion_chat.controller;

import com.example.emotion_chat.dto.ChatLogDTO;
import com.example.emotion_chat.dto.FaceDTO;
import com.example.emotion_chat.entity.ChatLog;
import com.example.emotion_chat.entity.User;
import com.example.emotion_chat.service.CFRFaceService;
import com.example.emotion_chat.service.ChatLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class FaceController {

  private final CFRFaceService faceService;
  private final ChatLogService chatLogService;

  @PostMapping("/face")
  @ResponseBody
  public String faceRecognition(@RequestParam("uploadFile") MultipartFile file, @RequestParam("id") long userId) {
    if (!file.isEmpty()) {
      try {
        ArrayList<FaceDTO> faceList = faceService.clovaFace(file);

        if (faceList.isEmpty()) {
          return "no_person";
        }

        ChatLog chatLog = new ChatLog();

        String emotion = faceList.get(0).getEmotion();
        switch (emotion) {
          case "laugh":
          case "smile":
            chatLog.setEmotion(ChatLog.Emotion.HAPPY);
            break;
          case "sad":
          case "fear":
            chatLog.setEmotion(ChatLog.Emotion.SAD);
            break;
          case "angry":
          case "disgust":
          case "surprise":
            chatLog.setEmotion(ChatLog.Emotion.ANGRY);
            break;
          case "neutral":
          case "talking":
            chatLog.setEmotion(ChatLog.Emotion.NEUTRAL);
            break;
        }

        chatLog.setUser(new User(userId));
        chatLog.setDateEntered(LocalDate.now());

        if (chatLogService.getEmotionsByUserIdAndDate(userId, LocalDate.now()) == null) {
          chatLogService.create(chatLog);
        }

        return "HAPPY";

      } catch (Exception e) {
        return "error";
      }
    }
    return "no_File";
  }
}
