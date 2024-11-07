package com.pettrip.service.care;

import com.pettrip.domain.care.CareRequest;
import com.pettrip.domain.enums.CareRequestStatus;
import com.pettrip.repository.CareRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class Scheduler {
    private final CareRequestRepository careRequestRepository;

    LocalDateTime now = LocalDateTime.now();

    @Scheduled(fixedRate = 1000)
    public void updateInProgressCareRequests() {
        List<CareRequest> requestsToStart = careRequestRepository
                .findByStatusAndStartDateBefore(CareRequestStatus.MATCHED, now);

        for (CareRequest careRequest : requestsToStart) {
            careRequest.setStatus(CareRequestStatus.IN_PROGRESS);
            careRequestRepository.save(careRequest);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void updateCompletedCareRequests() {
        List<CareRequest> requestsToComplete = careRequestRepository
                .findByStatusAndEndDateBefore(CareRequestStatus.IN_PROGRESS, now);

        for (CareRequest careRequest : requestsToComplete) {
            careRequest.setStatus(CareRequestStatus.COMPLETED);
            careRequestRepository.save(careRequest);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void updateExpiredCareRequests() {
        List<CareRequest> requestsToExpire = careRequestRepository
                .findByStatusAndStartDateBefore(CareRequestStatus.PENDING, now);

        for (CareRequest careRequest : requestsToExpire) {
            careRequest.setStatus(CareRequestStatus.EXPIRED);
            careRequestRepository.save(careRequest);
        }
    }


}
