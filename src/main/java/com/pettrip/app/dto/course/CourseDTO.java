package com.pettrip.app.dto.course;

import com.pettrip.domain.course.CourseTag;
import com.pettrip.domain.enums.CourseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseDTO {
        private String courseName;
        private List<CoordinateDTO> coordinates;
        private String courseDescription;
        private CourseStatus status;
        private Long userId;
        private List<String> tags;
        private String moveTime; // 이동 시간 (예: "00:01:04")
        private String province; // 광역시/도
        private String city; // 시/군/구
}
