package com.pettrip.repository;

import com.pettrip.domain.course.CourseTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseTagRepository extends JpaRepository<CourseTag, Long> {
    Optional<CourseTag> findByName(String name);
}
