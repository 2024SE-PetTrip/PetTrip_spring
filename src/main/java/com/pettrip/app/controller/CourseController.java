package com.pettrip.app.controller;
import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.SuccessStatus;
import com.pettrip.app.dto.course.CourseDTO;
import com.pettrip.app.dto.course.CourseResponseDTO;
import com.pettrip.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping("/create")
    public ApiResponse<CourseResponseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseResponseDTO courseResponseDTO = courseService.createCourse(courseDTO);
        return ApiResponse.of(SuccessStatus.COURSE_CREATED_OK, courseResponseDTO);
    }

    @PutMapping("/update/{courseId}")
    public ApiResponse<CourseResponseDTO> updateCourse(@PathVariable Long courseId, @RequestBody CourseDTO courseDTO) {
        CourseResponseDTO courseResponseDTO = courseService.updateCourse(courseId, courseDTO);
        return ApiResponse.of(SuccessStatus.COURSE_UPDATED_OK, courseResponseDTO);
    }
    @PutMapping("/delete/{courseId}")
    public ApiResponse<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ApiResponse.of(SuccessStatus.COURSE_DELETED_OK, null);
    }

}