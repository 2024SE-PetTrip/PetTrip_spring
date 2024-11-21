package com.pettrip.service.care;

import com.pettrip.apiPayload.code.status.ErrorStatus;
import com.pettrip.apiPayload.exception.handler.AppHandler;
import com.pettrip.app.dto.care.ChatRoomRequestDTO;
import com.pettrip.app.dto.care.ChatRoomResponseDTO;
import com.pettrip.converter.CareConverter;
import com.pettrip.domain.User;
import com.pettrip.domain.care.ChatRoom;
import com.pettrip.repository.ChatRoomRepository;
import com.pettrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    // private final SecurityUtil securityUtil;


    @Override // 개인 DM방 생성
    @Transactional
    public ChatRoomResponseDTO getOrCreateChatRoom(ChatRoomRequestDTO chatRoomRequestDTO) {
        // String id = securityUtil.getCurrentMemberUsername(); //id=roomMakerId 같아야 함
//        if (!id.equals(chatRoomRequestDTO.getRoomMakerId())) {
//            throw new AppHandler(ErrorStatus.NOT_FOUND_USER);
//        }
        User roomMaker = userRepository.findById(chatRoomRequestDTO.getRoomMakerId())
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));
        User guest = userRepository.findById(chatRoomRequestDTO.getGuestId())
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));

        Optional<ChatRoom> existingRoom = chatRoomRepository.findByChatRoomMembersContaining(roomMaker, guest);

        if (existingRoom.isPresent()) {
            ChatRoom room = existingRoom.get();
            return new ChatRoomResponseDTO(room.getId(), roomMaker.getId(), guest.getId());
        }

        ChatRoom newRoom = ChatRoom.create();
        newRoom.addMembers(roomMaker, guest);
        chatRoomRepository.save(newRoom);

        return new ChatRoomResponseDTO(newRoom.getId(), roomMaker.getId(), guest.getId());
    }

    @Override
    public List<ChatRoomResponseDTO> getUserChatRooms(Long userId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findAllByUserId(userId);

        return chatRooms.stream()
                .map(ChatRoom::toChatRoomResponseDTO)
                .collect(Collectors.toList());
    }


}
