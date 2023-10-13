package kr.megaptera.chatting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageResponseDto {
    private final Long id;

    private final String type;

    private final String value;

}
