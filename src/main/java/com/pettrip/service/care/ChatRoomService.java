package com.pettrip.service.care;

import com.pettrip.app.dto.care.ChatRoomRequestDTO;
import com.pettrip.app.dto.care.ChatRoomResponseDTO;

import java.util.List;

public interface ChatRoomService {

    ChatRoomResponseDTO getOrCreateChatRoom(ChatRoomRequestDTO chatRoomRequestDTO);

    List<ChatRoomResponseDTO> getUserChatRooms(Long userId);
}
