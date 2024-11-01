package com.pettrip.repository;

import com.pettrip.domain.User;
import com.pettrip.domain.care.CareRequest;
import com.pettrip.domain.enums.CareRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CareRequestRepository extends JpaRepository<CareRequest, Long> {
    Optional<CareRequest> findById(Long careRequestId);
    List<CareRequest> findByUserId(Long userId);
    List<CareRequest> findByStatus(CareRequestStatus status);
}
