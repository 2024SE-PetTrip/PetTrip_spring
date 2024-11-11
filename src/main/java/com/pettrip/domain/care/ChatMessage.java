package com.pettrip.domain.care;

import com.pettrip.domain.common.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "chat_message")
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(value = {AuditingEntityListener.class})
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage extends BaseEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private String roomId; //단순히 ID 값만 필요  (ChatRoom)

    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Long authorId;

    @Column(name = "message")
    private String message;
}
