package com.pettrip.domain.walk;

import com.pettrip.domain.User;
import com.pettrip.domain.common.BaseEntity;
import com.pettrip.domain.course.Course;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(nullable = false, length = 20)
    private String groupName; // 모임 이름

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course; // 코스 고유 코드

    @Column(nullable = false)
    private LocalDate startDate; // 모임 시작일

    @Column(nullable = false)
    private LocalDate endDate; // 모임 종료일

    @Column(nullable = false)
    private LocalDate walkingDate; // 산책 예정일

    @Column(nullable = false)
    private Integer maxParticipants; // 모임 최대 인원

    @Column
    private Integer maxPetsPerUser; // 인당 최대 동물 수

    @Column(nullable = false, length = 1000)
    private String groupDescription; // 모임 설명

    @Column(nullable = false, length = 255)
    private String groupAddress; // 지역

    @ManyToMany
    @JoinTable(
            name = "walk_group_tag_link",
            joinColumns = @JoinColumn(name = "walk_group_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<WalkGroupTag> tags; // 태그 리스트

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalkGroupUser> members; // 모임 멤버

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator; // 모임 생성자
}
