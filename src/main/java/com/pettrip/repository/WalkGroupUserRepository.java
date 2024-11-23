package com.pettrip.repository;

import com.pettrip.domain.walk.WalkGroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalkGroupUserRepository extends JpaRepository<WalkGroupUser, Long> {
    Optional<WalkGroupUser> findByGroup_GroupIdAndUser_IdAndIsApproved(Long groupId, Long userId, boolean isApproved);

}
