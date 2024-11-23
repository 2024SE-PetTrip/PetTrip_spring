package com.pettrip.converter;

import com.pettrip.app.dto.course.CourseResponseDTO;
import com.pettrip.domain.course.Course;
import com.pettrip.domain.course.Coordinate;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CourseConverter {

    public static CourseResponseDTO toCourseResponseDTO(Course course) {
        List<String> tags = course.getTags().stream()
                .map(tag -> tag.getName())
                .collect(Collectors.toList());

        List<CourseResponseDTO.CoordinateResponseDTO> coordinates = course.getCoordinates().stream()
                .map(CourseConverter::toCoordinateResponseDTO)
                .collect(Collectors.toList());

        return new CourseResponseDTO(
                course.getCourseId(),
                course.getCourseName(),
                course.getCourseDescription(),
                course.getMoveTime(),
                course.getProvince(),
                course.getCity(),
                course.getDistance(),
                course.getLikeCount(),
                course.getStatus().toString(),
                tags,
                coordinates,
                course.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                course.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }

    public static List<CourseResponseDTO> toCourseResponseDTOList(List<Course> courses) {
        return courses.stream()
                .map(CourseConverter::toCourseResponseDTO)
                .collect(Collectors.toList());
    }

    private static CourseResponseDTO.CoordinateResponseDTO toCoordinateResponseDTO(Coordinate coordinate) {
        CourseResponseDTO.CoordinateResponseDTO coordinateDTO = new CourseResponseDTO.CoordinateResponseDTO();
        coordinateDTO.setSequence(coordinate.getSequence());
        coordinateDTO.setLatitude(coordinate.getLatitude());
        coordinateDTO.setLongitude(coordinate.getLongitude());
        return coordinateDTO;
    }
}