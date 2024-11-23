package com.pettrip.service.course;

import com.pettrip.app.dto.course.CoordinateDTO;
import com.pettrip.app.dto.course.CourseDTO;
import com.pettrip.app.dto.course.CourseResponseDTO;
import com.pettrip.app.dto.course.CourseSearchDTO;
import com.pettrip.converter.CourseConverter;
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

import javax.transaction.Transactional;
import javax.xml.stream.Location;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void createCourse(CourseDTO courseDTO) {
        // 사용자 조회
        User user = userRepository.findById(courseDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        // 새로운 코스 생성
        Course course = new Course();
        course.setUser(user);
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseDescription(courseDTO.getCourseDescription() != null ? courseDTO.getCourseDescription() : "");
        course.setStatus(courseDTO.getStatus());
        course.setMoveTime(courseDTO.getMoveTime());
        course.setProvince(courseDTO.getProvince());
        course.setCity(courseDTO.getCity());
        course.setCreatedDate(LocalDateTime.now());
        course.setUpdatedDate(LocalDateTime.now());
        course.setLikeCount(0);

        // 태그 처리
        List<CourseTag> tags = new ArrayList<>();
        for (String tagName : courseDTO.getTags()) {
            CourseTag tag = courseTagRepository.findByName(tagName)
                    .orElseGet(() -> {
                        CourseTag newTag = new CourseTag();
                        newTag.setName(tagName);
                        return courseTagRepository.save(newTag);
                    });
            tags.add(tag);
        }
        course.setTags(tags);

        // 코스 저장
        course = courseRepository.save(course);

        List<Coordinate> coordinateList = new ArrayList<>();
        int sequenceCounter = 1; // 좌표의 순서를 지정할 카운터 초기화

        for (CoordinateDTO coordinateDTO : courseDTO.getCoordinates()) {
            Coordinate coordinate = new Coordinate();
            coordinate.setSequence(sequenceCounter++); // 현재 카운터 값을 sequence로 설정하고, 이후 증가
            coordinate.setLatitude(coordinateDTO.getLatitude());
            coordinate.setLongitude(coordinateDTO.getLongitude());
            coordinate.setCourse(course);
            coordinateList.add(coordinate);
        }

// 저장
        coordinateRepository.saveAll(coordinateList);

        // 저장된 좌표를 코스에 설정
        course.setCoordinates(coordinateList);

        // 이동 거리 계산
        course.updateDistance();

        // 코스 업데이트 (거리 반영)
        courseRepository.save(course);

    }

    public void updateCourse(Long courseId, CourseDTO courseDTO) {
        // 코스 조회
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));

        // 코스 제목 업데이트
        course.setCourseName(courseDTO.getCourseName());

        // 코스 설명 업데이트
        course.setCourseDescription(courseDTO.getCourseDescription() != null ? courseDTO.getCourseDescription() : "");

        // 공개 여부 업데이트
        course.setStatus(courseDTO.getStatus());

        // 이동 시간 업데이트
        course.setMoveTime(courseDTO.getMoveTime());

        // 지역 정보 업데이트
        course.setProvince(courseDTO.getProvince());
        course.setCity(courseDTO.getCity());

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
        courseRepository.save(course);
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

    public List<CourseResponseDTO> getAllCourses() {
        // ACTIVE 상태의 코스만 조회
        List<Course> activeCourses = courseRepository.findByStatus(CourseStatus.ACTIVE);
        return CourseConverter.toCourseResponseDTOList(activeCourses);
    }

    //searchCourses(searchDTO) (아직 구현 x)
    @Transactional
    public int increaseLikeCount(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));
        course.setLikeCount(course.getLikeCount() + 1);
        courseRepository.save(course);
        return course.getLikeCount();
    }

}

