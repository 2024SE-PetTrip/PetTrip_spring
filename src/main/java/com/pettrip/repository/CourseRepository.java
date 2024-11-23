package com.pettrip.repository;

import com.pettrip.domain.course.Course;
import com.pettrip.domain.enums.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Query("UPDATE Course c SET c.likeCount = c.likeCount + 1 WHERE c.courseId = :courseId")
    int incrementLikeCount(@Param("courseId") Long courseId);

    // 상태가 ACTIVE인 코스만 가져오는 메서드
    List<Course> findByStatus(CourseStatus status);
}
