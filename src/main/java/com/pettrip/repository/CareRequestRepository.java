package com.pettrip.repository;

import com.pettrip.domain.care.CareRequest;
import com.pettrip.domain.enums.CareRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CareRequestRepository extends JpaRepository<CareRequest, Long> {
    Optional<CareRequest> findById(Long careRequestId);
    List<CareRequest> findByRequesterId(Long requesterId);
    List<CareRequest> findByProviderId(Long providerId);

    List<CareRequest> findByStatus(CareRequestStatus status);

    @Query("SELECT c FROM CareRequest c WHERE c.status = :status AND c.startDate <= :now")
    List<CareRequest> findByStatusAndStartDateBefore(@Param("status")CareRequestStatus status, @Param("now") LocalDateTime now);

    @Query("SELECT c FROM CareRequest c WHERE c.status = :status AND c.endDate < :now")
    List<CareRequest> findByStatusAndEndDateBefore(@Param("status") CareRequestStatus status, @Param("now") LocalDateTime now);

}
