package com.pettrip.repository;

import com.pettrip.domain.walk.WalkGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalkGroupRepository extends JpaRepository<WalkGroup, Long> {
    Optional<WalkGroup> findById(Long groupId);
}
