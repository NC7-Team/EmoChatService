package com.example.emotion_chat.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestDto {
    private String type;

    private Long roomId;

    private Long userId;

    private String message;



}
