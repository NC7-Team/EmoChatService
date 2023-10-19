package com.example.emotion_chat.controller;

import com.example.emotion_chat.dto.ChatLogDTO;
import com.example.emotion_chat.dto.FaceDTO;
import com.example.emotion_chat.entity.ChatLog;
import com.example.emotion_chat.service.CFRFaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class FaceController {

  private final CFRFaceService faceService;

  @PostMapping("/face")
  @ResponseBody
  public String faceRecognition(@RequestParam("uploadFile") MultipartFile file, Model model) {
    if (!file.isEmpty()) {
      try {
        ArrayList<FaceDTO> faceList = faceService.clovaFace(file);

        if (faceList.isEmpty()) {
          return "no_person";
        }

        ChatLogDTO chatLogDTO = new ChatLogDTO();

        String emotion = faceList.get(0).getEmotion();
        switch (emotion) {
          case "laugh":
          case "smile":
            chatLogDTO.setEmotion(ChatLog.Emotion.HAPPY);
            break;
          case "sad":
          case "fear":
            chatLogDTO.setEmotion(ChatLog.Emotion.SAD);
            break;
          case "angry":
          case "disgust":
          case "surprise":
            chatLogDTO.setEmotion(ChatLog.Emotion.ANGRY);
            break;
          case "neutral":
          case "talking":
            chatLogDTO.setEmotion(ChatLog.Emotion.NEUTRAL);
            break;
        }

//        chatLogDTO.setUserId();
        chatLogDTO.setDateEntered(LocalDate.now());

        return chatLogDTO.getEmotion().name();

      } catch (Exception e) {
        return "error";
      }
    }
    return "no_File";
  }
}
