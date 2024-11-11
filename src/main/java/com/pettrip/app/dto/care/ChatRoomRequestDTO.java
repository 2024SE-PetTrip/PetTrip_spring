package com.pettrip.app.dto.care;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 채팅방 개설 요청 dto
 */
@Getter
@Setter
@NoArgsConstructor
public class ChatRoomRequestDTO {
    private Long roomMakerId;
    private Long guestId;
}
