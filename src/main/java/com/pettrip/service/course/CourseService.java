package com.pettrip.service.course;

import com.pettrip.app.dto.course.CoordinateDTO;
import com.pettrip.app.dto.course.CourseDTO;
import com.pettrip.domain.User;
import com.pettrip.domain.course.Coordinate;
import com.pettrip.domain.course.Course;
import com.pettrip.repository.CoordinateRepository;
import com.pettrip.repository.CourseRepository;
import com.pettrip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.Location;
import java.time.LocalDateTime;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoordinateRepository coordinateRepository;

    public void createCourse(CourseDTO courseDTO) {
        // 새로운 코스 생성

        User user = userRepository.findById(courseDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());

        LocalDateTime now = LocalDateTime.now();
        course.setCreatedDate(now);
        course.setUpdatedDate(now);

        course.setLikeCount(0);
        course.setCourseDescription(courseDTO.getCourseDescription() != null ? courseDTO.getCourseDescription() : "");
        course.setVisibility(courseDTO.getVisibility());

        course = courseRepository.save(course); // 코스를 저장하고 ID를 가져옴

        // 위치 데이터 저장
        for (CoordinateDTO coordinateDTO : courseDTO.getCoordinates()) {
            Coordinate coordinate = new Coordinate();
            coordinate.setSequence(coordinateDTO.getSequence());
            coordinate.setLatitude(coordinateDTO.getLatitude());
            coordinate.setLongitude(coordinateDTO.getLongitude());
            coordinate.setCourse(course); // 코스와 연결
            coordinateRepository.save(coordinate);
        }
        }
}

