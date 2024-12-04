package com.pettrip.repository;

import com.pettrip.domain.walk.WalkGroupTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalkGroupTagRepository extends JpaRepository<WalkGroupTag, Long> {
    Optional<WalkGroupTag> findByName(String name);
}
