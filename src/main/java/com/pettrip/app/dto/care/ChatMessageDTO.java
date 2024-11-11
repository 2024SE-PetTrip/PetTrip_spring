package com.pettrip.app.dto.care;

import com.pettrip.domain.care.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 웹소켓 접속시 요청 Dto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private String roomId;
    private Long authorId;
    private String message;
}
