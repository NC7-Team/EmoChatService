package com.example.emotion_chat.controller;

import com.example.emotion_chat.dto.RoomCreateRequest;
import com.example.emotion_chat.service.RoomInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

  private final RoomInfoService roomInfoService;

//  @PostMapping("/createOrJoin")
//  public RoomCreateRequest createOrJoinRoom(@RequestBody Map<String, String> requestBody) {
//    String roomTitle = requestBody.get("roomTitle");
//    RoomCreateRequest room = createOrJoinRoom(roomTitle);
//    return room;
//  }

  @GetMapping("/{roomIndex}/maxSize")
  public int getMaxRoomSize(@PathVariable int roomIndex) {
    return 2; // 최대 인원 수는 2명으로 설정
  }

//  private boolean isOverMaxSize() {
//    int currentRoomIndex = /* 현재 방 정보를 얻어오는 코드 작성 */;
//    int maxRoomSize = getMaxRoomSize(currentRoomIndex);
//    int userCount = roomInfoService.getUserCount(currentRoomIndex);
//    return userCount >= maxRoomSize;
//  }
//
//  private RoomCreateRequest createOrJoinRoom(String roomTitle) {
//    // 방 생성 요청 시 최대 인원 수 확인
//    if (isOverMaxSize()) {
//      return null; // 최대 인원 수를 초과하여 방 생성/입장 불가
//    } else {
//      int currentRoomIndex = /* 현재 방 정보를 얻어오는 코드 작성 */;
//      return roomInfoService.createRoom(roomTitle);
//    }
//  }
}
