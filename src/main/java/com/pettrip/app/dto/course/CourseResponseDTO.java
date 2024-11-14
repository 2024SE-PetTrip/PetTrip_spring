package com.pettrip.app.dto.course;

import com.pettrip.domain.enums.CourseStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CourseResponseDTO {
    private Long courseId;
    private String courseName;
    private String courseDescription;
    private CourseStatus status;
    private double distance;
    private String courseAddress;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<String> tags; // 태그 목록

    // 생성자를 오버로딩하여 courseId만 반환하는 경우
    public CourseResponseDTO(Long courseId) {
        this.courseId = courseId;
    }

    // 생성자
    public CourseResponseDTO(Long courseId, String courseName, String courseDescription, CourseStatus status, double distance, String courseAddress, LocalDateTime createdDate, LocalDateTime updatedDate, List<String> tags) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.status = status;
        this.distance = distance;
        this.courseAddress = courseAddress;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.tags = tags;
    }
}
