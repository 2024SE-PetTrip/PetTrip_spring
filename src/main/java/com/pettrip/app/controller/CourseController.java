package com.pettrip.app.controller;
import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.SuccessStatus;
import com.pettrip.app.dto.course.CourseDTO;
import com.pettrip.app.dto.course.CourseResponseDTO;
import com.pettrip.app.dto.course.CourseSearchDTO;
import com.pettrip.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping("/create")
    public ApiResponse<Void> createCourse(@RequestBody CourseDTO courseDTO) {
        courseService.createCourse(courseDTO);
        return ApiResponse.of(SuccessStatus.COURSE_CREATED_OK, null);
    }

    @PutMapping("/update/{courseId}")
    public ApiResponse<Void> updateCourse(@PathVariable Long courseId, @RequestBody CourseDTO courseDTO) {
        courseService.updateCourse(courseId, courseDTO);
        return ApiResponse.of(SuccessStatus.COURSE_UPDATED_OK, null);
    }

    @PutMapping("/delete/{courseId}")
    public ApiResponse<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ApiResponse.of(SuccessStatus.COURSE_DELETED_OK, null);
    }

    @GetMapping("/all")
    public ApiResponse<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> allCourses = courseService.getAllCourses();
        return ApiResponse.of(SuccessStatus.COURSE_LIST_OK, allCourses);
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<CourseResponseDTO>> getCoursesByUserId(@PathVariable Long userId) {
        List<CourseResponseDTO> userCourses = courseService.getCoursesByUserId(userId);
        return ApiResponse.of(SuccessStatus.COURSE_LIST_OK, userCourses);
    }
    /*
    @GetMapping("/search")
    public ApiResponse<List<CourseResponseDTO>> searchCourses(@RequestBody CourseSearchDTO searchDTO) {
        List<CourseResponseDTO> resultCourseList = courseService.searchCourses(searchDTO);
        return ApiResponse.of(SuccessStatus.COURSE_LIST_OK, resultCourseList);
    }

     */ //검색 부분은 회의가 진행된 이후 개발 예정, 제목은 검색 / 태그는 필터링 방식으로 할건지에 대한 논의가 필요함.

    @PostMapping("/{courseId}/like")
    public ApiResponse<Integer> likeCourse(@PathVariable Long courseId) {
        int newLikeCount = courseService.increaseLikeCount(courseId);
        return ApiResponse.of(SuccessStatus.LIKE_SUCCESS, newLikeCount);
    }

    @PostMapping("/{courseId}/dislike")
    public ApiResponse<Integer> dislikeCourse(@PathVariable Long courseId) {
        int newLikeCount = courseService.decreaseLikeCount(courseId);
        return ApiResponse.of(SuccessStatus.DISLIKE_SUCCESS, newLikeCount);
    }

}