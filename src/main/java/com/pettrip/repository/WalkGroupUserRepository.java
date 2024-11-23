package com.pettrip.repository;

import com.pettrip.domain.walk.WalkGroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkGroupUserRepository extends JpaRepository<WalkGroupUser, Long> {
}
