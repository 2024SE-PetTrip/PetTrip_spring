package com.pettrip.service.care;

import com.pettrip.app.dto.care.CareRequestDTO;
import com.pettrip.app.dto.care.CareResponseDTO;
import com.pettrip.app.dto.care.EvaluationRequestDTO;
import com.pettrip.app.dto.care.EvaluationResponseDTO;
import com.pettrip.domain.enums.CareRequestStatus;

import java.util.List;

public interface CareService {

    // 돌봄 요청 생성
    CareResponseDTO.AddCareDTO createCareRequest(CareRequestDTO careRequestDto);

    // 모든 돌봄 요청 조회
    List<CareResponseDTO.GetCareDTO> getAllCareRequest();

    // 특정 돌봄 요청 조회 (ID로 조회)
    CareResponseDTO.GetCareDetailDTO getCareRequestById(Long requestId);

    // 상태별 돌봄 요청 목록 조회
    List<CareResponseDTO.GetCareDTO> getCareRequestsByStatus(CareRequestStatus status);

    // 돌봄 요청 필터링 검색
    List<CareResponseDTO.GetCareDTO> getCareRequestsByFilter(String address, String breed);

    // 돌봄 요청 업데이트 (예: 상태 업데이트, 내용 변경 등)
    CareResponseDTO.UpdateCareDTO updateCareRequest(Long requestId, CareRequestDTO careRequestDto);

    // 돌봄 요청 삭제
    void deleteCareRequest(Long requestId);

    // 돌봄 제공자가 요청을 수락하거나 매칭하는 기능
    CareResponseDTO.MatchCareProviderDTO matchCareProvider(Long requestId, Long providerId);

    // 돌봄 요청 완료로 업데이트
    CareResponseDTO.UpdateCareDTO updateCareRequestAsCompleted(Long requestId);

    // 돌봄 서비스 완료 후 평가
    EvaluationResponseDTO addEvaluation(Long requestId, EvaluationRequestDTO requestDto);

    // 사용자의 돌봄 평가 내역 불러오기
    List<EvaluationResponseDTO> getEvaluationsByUserId(Long providerId);
}
