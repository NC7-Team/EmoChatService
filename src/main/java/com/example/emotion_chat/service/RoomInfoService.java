package com.example.emotion_chat.service;

import com.example.emotion_chat.dto.RoomCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class RoomInfoService {


  private final Map<Integer, RoomCreateRequest> roomInfoMap = new ConcurrentHashMap<>();
  private final Map<Integer, Integer> roomUserCount = new ConcurrentHashMap<>();
  private AtomicInteger roomIndexCounter = new AtomicInteger(10);

  public RoomCreateRequest createRoom(String roomTitle) {
    int roomIndex = roomIndexCounter.getAndIncrement();
    RoomCreateRequest roomCreateRequest = new RoomCreateRequest(roomIndex, roomTitle);
    roomInfoMap.put(roomIndex, roomCreateRequest);
    return roomCreateRequest;
  }

  public RoomCreateRequest getRoomInfo(int roomIndex) {
    return roomInfoMap.get(roomIndex);
  }


  public List<RoomCreateRequest> getAllRooms() {
    List<RoomCreateRequest> rooms = new ArrayList<>();
    for (Map.Entry<Integer, RoomCreateRequest> entry : roomInfoMap.entrySet()) {
      rooms.add(entry.getValue());
    }
    return rooms;
  }

  public void deleteRoom(int roomIndex) {
    roomInfoMap.remove(roomIndex);
    roomUserCount.remove(roomIndex);
  }

  public int getUserCount(int roomIndex) {
    return roomUserCount.getOrDefault(roomIndex, 0);
  }


}
