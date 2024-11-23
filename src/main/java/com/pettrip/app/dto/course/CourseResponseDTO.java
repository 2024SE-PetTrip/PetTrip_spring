package com.pettrip.app.dto.course;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponseDTO {
    private Long courseId; // 코스 ID
    private String courseName; // 코스 이름
    private String courseDescription; // 코스 설명
    private String moveTime; // 이동 시간
    private String province; // 광역시/도
    private String city; // 시/군/구
    private double distance; // 이동 거리
    private int likeCount; // 좋아요 수
    private String status; // 상태 (DELETE, PROTECTED, ACTIVE)
    private List<String> tags; // 태그 목록
    private List<CoordinateResponseDTO> coordinates; // 좌표 리스트
    private String createdDate; // 생성일 (포맷팅된 문자열)
    private String updatedDate; // 수정일 (포맷팅된 문자열)

    // 좌표 응답용 DTO
    @Getter
    @Setter
    public static class CoordinateResponseDTO {
        private int sequence;
        private double latitude;
        private double longitude;
    }

    public CourseResponseDTO(Long courseId, String courseName, String courseDescription, String moveTime,
                             String province, String city, double distance, int likeCount, String status,
                             List<String> tags, List<CoordinateResponseDTO> coordinates,
                             String createdDate, String updatedDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.moveTime = moveTime;
        this.province = province;
        this.city = city;
        this.distance = distance;
        this.likeCount = likeCount;
        this.status = status;
        this.tags = tags;
        this.coordinates = coordinates;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}