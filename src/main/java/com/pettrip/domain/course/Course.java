package com.pettrip.domain.course;

import com.pettrip.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId; // AUTO_INCREMENT ID

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 코스를 만든 사용자와의 관계

    private String courseName; // 코스 이름 또는 추가 정보

    @OneToMany(mappedBy = "course")
    private List<Coordinate> coordinates; // 이 코스에 속한 좌표들

    @Column
    private double distance; // 코스 거리

    @Column
    private LocalDateTime startTime; // 코스 시작 시간

    @Column
    private LocalDateTime endTime; // 코스 종료 시간

    @Column(length = 255)
    private String courseAddress; // 코스 주소

    @Column
    private int visibility; // 코스 공개 여부 (0: 비공개, 1: 공개 등)

    @Column(length = 1000)
    private String courseDescription; // 코스 설명

    @Column
    private int likeCount; // 좋아요 수

    @Column
    private LocalDateTime createdDate; // 코스 생성일

    @Column
    private LocalDateTime updatedDate; // 코스 수정일
}