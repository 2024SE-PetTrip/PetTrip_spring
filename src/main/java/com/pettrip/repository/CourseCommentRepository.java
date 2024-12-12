package com.pettrip.repository;

import com.pettrip.domain.course.CourseComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseCommentRepository extends JpaRepository<CourseComment, Long> {
    List<CourseComment> findByCourse_CourseId(Long courseId);
}