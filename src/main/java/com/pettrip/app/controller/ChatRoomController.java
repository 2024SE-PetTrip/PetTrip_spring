package com.pettrip.app.controller;

import com.pettrip.app.dto.care.ChatRoomRequestDTO;
import com.pettrip.app.dto.care.ChatRoomResponseDTO;
import com.pettrip.service.care.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/care/chatRoom/")
@Slf4j
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping("/personal") // 개인 DM 채팅방 생성 또는 가져오기
    public ChatRoomResponseDTO getOrCreatePersonalChatRoom(@RequestBody ChatRoomRequestDTO chatRoomRequestDTO) {
        return chatRoomService.getOrCreateChatRoom(chatRoomRequestDTO);
    }

    @GetMapping("/{userId}/myChatRooms")
    public List<ChatRoomResponseDTO> getUserChatRooms(
            @PathVariable Long userId) {
        return chatRoomService.getUserChatRooms(userId);
    }

}
