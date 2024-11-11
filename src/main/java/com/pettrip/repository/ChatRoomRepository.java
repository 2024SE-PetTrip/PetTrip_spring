package com.pettrip.repository;

import com.pettrip.domain.User;
import com.pettrip.domain.care.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    @Query("SELECT cr FROM ChatRoom cr JOIN cr.chatRoomMembers m1 JOIN cr.chatRoomMembers m2 " +
            "WHERE m1 = :roomMaker AND m2 = :guest")
    Optional<ChatRoom> findByChatRoomMembersContaining(@Param("roomMaker") User roomMaker,
                                                       @Param("guest") User guest);
}
