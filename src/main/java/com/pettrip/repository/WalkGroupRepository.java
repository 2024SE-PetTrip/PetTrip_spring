package com.pettrip.repository;

import com.pettrip.domain.walk.WalkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WalkGroupRepository extends JpaRepository<WalkGroup, Long> {
    Optional<WalkGroup> findById(Long groupId);

    @Query("SELECT DISTINCT wg FROM WalkGroup wg " +
            "LEFT JOIN wg.tags tag " +
            "WHERE (:address IS NULL OR wg.groupAddress = :address) " +
            "AND (:tags IS NULL OR tag.name IN (:tags))")
    List<WalkGroup> findByAddressAndAnyTags(
            @Param("address") String address,
            @Param("tags") List<String> tags);

}
