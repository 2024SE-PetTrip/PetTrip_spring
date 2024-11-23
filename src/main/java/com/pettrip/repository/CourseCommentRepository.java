package com.pettrip.repository;

import com.pettrip.domain.course.CourseComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCommentRepository extends JpaRepository<CourseComment, Long> {
}