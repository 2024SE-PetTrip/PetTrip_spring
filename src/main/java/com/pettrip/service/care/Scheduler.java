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

    @Scheduled(fixedRate = 1000)
    public void updateInProgressCareRequests() {
        LocalDateTime now = LocalDateTime.now();

        List<CareRequest> requestsToStart = careRequestRepository
                .findByStatusAndStartDateBefore(CareRequestStatus.MATCHED, now);

        for (CareRequest careRequest : requestsToStart) {
            careRequest.setStatus(CareRequestStatus.IN_PROGRESS);
            careRequestRepository.save(careRequest);
        }
    }

    @Scheduled(fixedRate = 1000) // Executes every second
    public void updateCompletedCareRequests() {
        LocalDateTime now = LocalDateTime.now();

        List<CareRequest> requestsToComplete = careRequestRepository
                .findByStatusAndEndDateBefore(CareRequestStatus.IN_PROGRESS, now);

        for (CareRequest careRequest : requestsToComplete) {
            careRequest.setStatus(CareRequestStatus.COMPLETED);
            careRequestRepository.save(careRequest);
        }
    }

    @Scheduled(fixedRate = 1000) // Executes every second
    public void updateExpiredCareRequests() {
        LocalDateTime now = LocalDateTime.now();

        List<CareRequest> requestsToExpire = careRequestRepository
                .findByStatusAndStartDateBefore(CareRequestStatus.PENDING, now);

        for (CareRequest careRequest : requestsToExpire) {
            careRequest.setStatus(CareRequestStatus.EXPIRED);
            careRequestRepository.save(careRequest);
        }
    }

    @Scheduled(fixedRate = 1000) // 1초마다 실행
    public void resetExpiredToPending() {
        LocalDateTime now = LocalDateTime.now();

        // 상태가 EXPIRED인데 시작 시간이 현재 시간 이후인 요청들
        List<CareRequest> requestsToReset = careRequestRepository
                .findByStatusAndStartDateAfter(CareRequestStatus.EXPIRED, now);

        for (CareRequest careRequest : requestsToReset) {
            careRequest.setStatus(CareRequestStatus.PENDING);
            careRequestRepository.save(careRequest);
        }
    }
}

