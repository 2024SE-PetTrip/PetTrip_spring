package com.pettrip.app.dto.course;

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
        private int visibility;
        private Long userId;
        //코스 시작 버튼을 누른시점과 종료버튼을 누른 시점에 대한 코드가 없긴함.
}

