package com.pettrip.app.controller;
import com.pettrip.app.dto.course.CourseDTO;
import com.pettrip.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public String createCourse(@RequestBody CourseDTO courseDTO) {
        courseService.createCourse(courseDTO);
        return "Course created successfully!";
    }
}