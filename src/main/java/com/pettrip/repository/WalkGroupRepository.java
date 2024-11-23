package com.pettrip.repository;

import com.pettrip.domain.walk.WalkGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkGroupRepository extends JpaRepository<WalkGroup, Long> {
}
