package com.pettrip.service.course;

import com.pettrip.app.dto.course.CoordinateDTO;
import com.pettrip.app.dto.course.CourseDTO;
import com.pettrip.app.dto.course.CourseResponseDTO;
import com.pettrip.domain.User;
import com.pettrip.domain.course.Coordinate;
import com.pettrip.domain.course.Course;
import com.pettrip.domain.course.CourseTag;
import com.pettrip.domain.enums.CourseStatus;
import com.pettrip.repository.CoordinateRepository;
import com.pettrip.repository.CourseRepository;
import com.pettrip.repository.CourseTagRepository;
import com.pettrip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.Location;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Autowired
    private CourseTagRepository courseTagRepository;

    public CourseResponseDTO createCourse(CourseDTO courseDTO) {
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
        course.setStatus(courseDTO.getStatus());

        List<CourseTag> tags = new ArrayList<>();
        for (String tagName : courseDTO.getTags()) {
            CourseTag tag = courseTagRepository.findByName(tagName)
                    .orElseGet(() -> {
                        // 새로운 태그 저장
                        CourseTag newTag = new CourseTag();
                        newTag.setName(tagName);
                        return courseTagRepository.save(newTag);
                    });
            tags.add(tag);
        }
        course.setTags(tags);

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

        return new CourseResponseDTO(course.getCourseId());
    }

    public CourseResponseDTO updateCourse(Long courseId, CourseDTO courseDTO) {
        // 코스 조회
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));

        // 코스 제목 업데이트
        course.setCourseName(courseDTO.getCourseName());

        // 코스 설명 업데이트
        course.setCourseDescription(courseDTO.getCourseDescription() != null ? courseDTO.getCourseDescription() : "");

        // 공개 여부 업데이트
        course.setStatus(courseDTO.getStatus());

        // 기존 태그 제거
        course.getTags().clear();
        // 태그 업데이트
        List<CourseTag> tags = new ArrayList<>();
        for (String tagName : courseDTO.getTags()) {
            CourseTag tag = courseTagRepository.findByName(tagName)
                    .orElseGet(() -> {
                        // 새로운 태그 저장
                        CourseTag newTag = new CourseTag();
                        newTag.setName(tagName);
                        return courseTagRepository.save(newTag);
                    });
            tags.add(tag);
        }
        course.setTags(tags);

        // 코스 업데이트 시간 설정
        course.setUpdatedDate(LocalDateTime.now());

        // 코스 저장
        course = courseRepository.save(course);

        return new CourseResponseDTO(course.getCourseId());
    }

    public void deleteCourse(Long courseId) {
        // 코스를 가져오기
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));

        // 코스 상태를 DELETE로 변경
        course.setStatus(CourseStatus.DELETE);
        course.setUpdatedDate(LocalDateTime.now());

        // 코스 저장
        courseRepository.save(course);
    }
}

