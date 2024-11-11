package com.pettrip.service.care;

import com.pettrip.apiPayload.code.status.ErrorStatus;
import com.pettrip.apiPayload.exception.handler.AppHandler;
import com.pettrip.app.dto.care.ChatMessageDTO;
import com.pettrip.converter.CareConverter;
import com.pettrip.domain.care.ChatMessage;
import com.pettrip.domain.care.ChatRoom;
import com.pettrip.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService{

    private final ChatRoomRepository chatRoomRepository;

    @Override
    @Transactional
    public ChatMessage createChatMessage(ChatMessageDTO chatMessageDto) {
        ChatMessage chatMessage = CareConverter.toChatMessage(chatMessageDto);
        ChatRoom chatRoom = chatRoomRepository.findById(chatMessage.getRoomId())
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_CHATROOM));
        chatRoom.setLastChatMesg(chatMessage);
        chatRoomRepository.save(chatRoom);

        return chatMessage;
    }
}
