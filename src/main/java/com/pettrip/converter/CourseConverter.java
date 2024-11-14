package com.pettrip.converter;

import com.pettrip.app.dto.course.CourseResponseDTO;
import com.pettrip.domain.course.Course;
import java.util.List;
import java.util.stream.Collectors;

public class CourseConverter {

    public static CourseResponseDTO toCourseResponseDTO(Course course) {
        List<String> tags = course.getTags().stream()
                .map(tag -> tag.getName())
                .collect(Collectors.toList());

        return new CourseResponseDTO(
                course.getCourseId(),
                course.getCourseName(),
                course.getCourseDescription(),
                course.getStatus(),
                course.getDistance(),
                course.getCourseAddress(),
                course.getCreatedDate(),
                course.getUpdatedDate(),
                tags
        );
    }

    public static List<CourseResponseDTO> toCourseResponseDTOList(List<Course> courses) {
        return courses.stream()
                .map(CourseConverter::toCourseResponseDTO)
                .collect(Collectors.toList());
    }
}
