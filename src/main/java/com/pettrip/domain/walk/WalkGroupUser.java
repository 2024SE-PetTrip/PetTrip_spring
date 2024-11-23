package com.pettrip.domain.walk;

import com.pettrip.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkGroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private WalkGroup group; // 어떤 모임인지 참조

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 어떤 유저인지 참조

    @Column(nullable = false)
    private boolean isApproved; // 참가 승인 여부 (모임 생성자가 승인한 경우 true)

}
