package com.pettrip.app.dto.course;

import lombok.Getter;
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
}

