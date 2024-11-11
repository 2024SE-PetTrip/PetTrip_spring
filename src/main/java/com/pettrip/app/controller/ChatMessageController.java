package com.pettrip.app.controller;

import com.pettrip.app.dto.care.ChatMessageDTO;
import com.pettrip.domain.care.ChatMessage;
import com.pettrip.service.care.ChatMessageService;
import com.pettrip.service.care.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatMessageController {
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/message")
    public void sendMessage(ChatMessageDTO chatMessageDTO) {
        // 실시간으로 방에서 채팅하기
        ChatMessage newChat = chatMessageService.createChatMessage(chatMessageDTO);
        log.info("received message: {}", chatMessageDTO);

        // 방에 있는 모든 사용자에게 메시지 전송
        messagingTemplate.convertAndSend("/sub/channel/"+chatMessageDTO.getRoomId(), newChat);
    }
}
