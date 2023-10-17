package com.example.emotion_chat.controller;

import com.example.emotion_chat.dto.FaceDTO;
import com.example.emotion_chat.service.CFRFaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
        // Call face recognition service
        ArrayList<FaceDTO> faceList = faceService.clovaFace(file);

        if (faceList.isEmpty()) {
          return "no_person";
        }

        String emotion = faceList.get(0).getEmotion();
        switch (emotion) {
          case "angry", "disgust", "fear":
            return "angry";
          case "sad", "neutral", "surprise":
            return "sad";
          case "laugh", "smile", "talking":
            return "happy";
        }

      } catch (Exception e) {
        return "error";
      }
    }
    return "no_File";
  }
}
