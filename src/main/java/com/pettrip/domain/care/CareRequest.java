package com.pettrip.domain.care;

import com.pettrip.app.dto.CareRequestDTO;
import com.pettrip.domain.User;
import com.pettrip.domain.common.BaseEntity;
import com.pettrip.domain.enums.CareRequestStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareRequest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime requestDate;

    @Column(nullable = false, length = 1000)
    private String requestDescription;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'PENDING'")
    private CareRequestStatus status;

    @Column(length = 255)
    private String requestImageUrl;
}
