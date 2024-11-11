package com.pettrip.app.dto.care;

import lombok.*;

/**
 * 채팅방 개설 성공시 응답 dto
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ChatRoomResponseDTO {
    private String chatRoomId;
    private Long roomMakerId;
    private Long guestId;
}
