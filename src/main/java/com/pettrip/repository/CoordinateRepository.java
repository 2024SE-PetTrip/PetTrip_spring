package com.pettrip.repository;
import com.pettrip.domain.course.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {

}