package com.pettrip.service.care;


import com.pettrip.app.dto.care.ChatMessageDTO;
import com.pettrip.domain.care.ChatMessage;

public interface ChatMessageService {
    public ChatMessage createChatMessage(ChatMessageDTO chatMessageDto);
}
