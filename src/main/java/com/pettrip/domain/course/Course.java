package com.pettrip.domain.course;

import com.pettrip.domain.User;
import com.pettrip.domain.enums.CourseStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Duration;
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

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Coordinate> coordinates; // 이 코스에 속한 좌표들

    @ManyToMany
    @JoinTable(
            name = "course_tag_link",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "course_tag_id")
    )
    private List<CourseTag> tags; // 코스에 연결된 태그들

    @Column
    private double distance; // 코스 거리

    @Column
    private LocalDateTime startTime; // 코스 시작 시간

    @Column
    private LocalDateTime endTime; // 코스 종료 시간

    @Column(length = 255)
    private String courseAddress; // 코스 주소

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseStatus status; // 코스 상태 (DELETE, PROTECTED, ACTIVE)

    @Column(length = 1000)
    private String courseDescription; // 코스 설명

    @Column
    private int likeCount; // 좋아요 수

    @Column
    private LocalDateTime createdDate; // 코스 생성일

    @Column
    private LocalDateTime updatedDate; // 코스 수정일

    public String getDuration() {
        if (startTime != null && endTime != null) {
            Duration duration = Duration.between(startTime, endTime);
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            long seconds = duration.getSeconds() % 60;
            if (hours > 0) {
                return String.format("%d시간 %d분 %d초", hours, minutes, seconds);
            } else {
                return String.format("%d분 %d초", minutes, seconds);
            }
        }
        return "시간 정보 없음";
    }

    public void updateDistance() {
        //좌표값이 없을때 return
        if (coordinates == null || coordinates.size() < 2) {
            distance = 0.0;
            return;
        }
        distance = 0.0;
        for (int i = 1; i < coordinates.size(); i++) {
            Coordinate prev = coordinates.get(i - 1);
            Coordinate current = coordinates.get(i);
            distance += calculateDistance(prev, current);
        }
    }

    private double calculateDistance(Coordinate c1, Coordinate c2) {
        double earthRadius = 6371e3; // Radius in meters
        double lat1 = Math.toRadians(c1.getLatitude());
        double lat2 = Math.toRadians(c2.getLatitude());
        double deltaLat = Math.toRadians(c2.getLatitude() - c1.getLatitude());
        double deltaLon = Math.toRadians(c2.getLongitude() - c1.getLongitude());

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c; // Distance in meters
    }
}