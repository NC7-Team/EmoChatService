package com.example.emotion_chat.service;

import com.example.emotion_chat.config.NcpConfig;
import com.example.emotion_chat.dto.FaceDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Service
public class CFRFaceService {

  @Autowired
  private NcpConfig ncpConfig;

  public ArrayList<FaceDTO> clovaFace(MultipartFile imageFile) {
    ArrayList<FaceDTO> faceList = new ArrayList<>();

    try {
      String paramName = "image";
      byte[] fileBytes = imageFile.getBytes();

      URL url = new URL(ncpConfig.getApiURL());
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setUseCaches(false);
      con.setDoOutput(true);
      con.setDoInput(true);

      String boundary = "---" + System.currentTimeMillis() + "---";
      con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
      con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", ncpConfig.getClientId());
      con.setRequestProperty("X-NCP-APIGW-API-KEY", ncpConfig.getClientSecret());

      OutputStream outputStream = con.getOutputStream();
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);
      String LINE_FEED = "\r\n";

      String fileName = imageFile.getOriginalFilename();
      writer.append("--" + boundary).append(LINE_FEED);
      writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
      writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
      writer.append(LINE_FEED);
      writer.flush();

      outputStream.write(fileBytes, 0, fileBytes.length);
      outputStream.flush();

      writer.append(LINE_FEED).flush();
      writer.append("--" + boundary + "--").append(LINE_FEED);
      writer.close();

      BufferedReader br;
      int responseCode = con.getResponseCode();

      if (responseCode == 200) {
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      } else {
        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
      }

      StringBuilder response = new StringBuilder();
      String inputLine;
      while ((inputLine = br.readLine()) != null) {
        response.append(inputLine);
      }
      br.close();

      faceList = jsonToVoList(response.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return faceList;
  }

  public ArrayList<FaceDTO> jsonToVoList(String jsonResultStr) {
    ArrayList<FaceDTO> faceList = new ArrayList<>();

    try {
      JSONParser jsonParser = new JSONParser();
      JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonResultStr);
      JSONArray facesArray = (JSONArray) jsonObj.get("faces");

      if (facesArray != null) {
        for (Object faceObj : facesArray) {
          JSONObject face = (JSONObject) faceObj;

          JSONObject emotions = (JSONObject) face.get("emotion");
          JSONObject age = (JSONObject) face.get("age");
          JSONObject gender = (JSONObject) face.get("gender");

          String genderValue = (String) gender.get("value");
          double genderConfidence = (double) gender.get("confidence");
          String ageValue = (String) age.get("value");
          double ageConfidence = (double) age.get("confidence");
          String emotionValue = (String) emotions.get("value");
          double emotionConfidence = (double) emotions.get("confidence");

          FaceDTO vo = new FaceDTO();
          vo.setGender(genderValue);
          vo.setGenderConfidence(genderConfidence);
          vo.setAge(ageValue);
          vo.setAgeConfidence(ageConfidence);
          vo.setEmotion(emotionValue);
          vo.setEmotionConfidence(emotionConfidence);

          faceList.add(vo);
        }
      } else {
        FaceDTO vo = new FaceDTO();
        vo.setGender("없음");
        vo.setGenderConfidence(0);
        vo.setAge("없음");
        vo.setAgeConfidence(0);
        vo.setEmotion("없음");
        vo.setEmotionConfidence(0);

        faceList.add(vo);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return faceList;
  }
}
