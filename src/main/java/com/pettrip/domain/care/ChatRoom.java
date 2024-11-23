package com.pettrip.domain.care;

import com.pettrip.app.dto.care.ChatRoomResponseDTO;
import com.pettrip.domain.User;
import com.pettrip.domain.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "chat_room")
@DynamicUpdate
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(value = {AuditingEntityListener.class})
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseEntity {
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    private String id;

    //단방향
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "last_chat_mesg_id")
    private ChatMessage lastChatMesg;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "chat_room_members",
            joinColumns = @JoinColumn(name = "chat_room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> chatRoomMembers = new HashSet<>();

    public static ChatRoom create() {

        ChatRoom room = new ChatRoom();
        room.setId(UUID.randomUUID().toString());

        return room;
    }

    public void addMembers(User roomMaker, User guest) {
        this.chatRoomMembers.add(roomMaker);
        this.chatRoomMembers.add(guest);
    }

    public static ChatRoomResponseDTO toChatRoomResponseDTO(ChatRoom chatRoom) {
        Long roomMakerId = chatRoom.getChatRoomMembers().stream()
                .findFirst()
                .map(User::getId)
                .orElse(null);

        Long guestId = chatRoom.getChatRoomMembers().stream()
                .skip(1)
                .map(User::getId)
                .findFirst()
                .orElse(null);

        return ChatRoomResponseDTO.builder()
                .chatRoomId(chatRoom.getId())
                .roomMakerId(roomMakerId)
                .guestId(guestId)
                .build();
    }
}
