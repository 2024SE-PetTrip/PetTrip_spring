package com.pettrip.service.care;

import com.pettrip.app.dto.CareRequestDTO;
import com.pettrip.app.dto.CareResponseDTO;
import com.pettrip.domain.care.CareRequest;
import com.pettrip.domain.care.Evaluation;
import com.pettrip.domain.enums.CareRequestStatus;

import java.util.List;

public interface CareService {

    // 돌봄 요청 생성
    CareResponseDTO createCareRequest(CareRequestDTO careRequestDto);

    List<CareResponseDTO> getAllCareRequest();

    // 특정 돌봄 요청 조회 (ID로 조회)
    CareRequest getCareRequestById(Long requestId);

    // 사용자별 돌봄 요청 목록 조회
    List<CareRequest> getCareRequestsByUserId(Long userId);

    // 상태별 돌봄 요청 목록 조회
    List<CareRequest> getCareRequestsByStatus(CareRequestStatus status);

    // 돌봄 요청 업데이트 (예: 상태 업데이트, 내용 변경 등)
    CareRequest updateCareRequest(Long requestId, CareRequestDTO careRequestDto);

    // 돌봄 요청 삭제
    void deleteCareRequest(Long requestId);

    // 돌봄 제공자가 요청을 수락하거나 매칭하는 기능
    CareRequest matchCareProvider(Long requestId, Long providerId);

    // 돌봄 서비스 완료 후 평가 추가
    // Evaluation addEvaluation(Long requestId, EvaluationDto evaluationDto);

    // 돌봄 서비스 완료 상태로 업데이트
    CareRequest completeCareRequest(Long requestId);

    // 돌봄 요청자가 돌봄 제공자와 실시간 채팅 생성
    // ChatRoom createChatRoom(Long requestId, Long providerId);
}
