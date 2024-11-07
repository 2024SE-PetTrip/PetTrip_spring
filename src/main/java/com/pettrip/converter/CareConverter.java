package com.pettrip.converter;

import com.pettrip.app.dto.care.CareRequestDTO;
import com.pettrip.app.dto.care.CareResponseDTO;
import com.pettrip.app.dto.care.EvaluationResponseDTO;
import com.pettrip.domain.care.CareRequest;
import com.pettrip.domain.care.Evaluation;
import com.pettrip.domain.enums.CareRequestStatus;

import javax.persistence.Convert;
import java.util.List;
import java.util.stream.Collectors;

@Convert
public class CareConverter {

    public static CareRequest toCareRequest(CareRequestDTO dto) {
        return CareRequest.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .requestDescription(dto.getRequestDescription())
                .requestImageUrl(dto.getRequestImageUrl())
                .status(CareRequestStatus.PENDING)
                .build();
    }

    // CareRequest 엔티티를 CareResponseDTO로 변환 (상세 정보)
    public static CareResponseDTO toCareResponseDTO(CareRequest careRequest) {
        return CareResponseDTO.builder()
                .requestId(careRequest.getRequestId())
                .requesterId(careRequest.getRequester().getId()) //돌봄 요청자 DTO
                .startDate(careRequest.getStartDate())
                .endDate(careRequest.getEndDate())
                .requestDescription(careRequest.getRequestDescription())
                .requestImageUrl(careRequest.getRequestImageUrl())
                .status(careRequest.getStatus())
                .providerId(careRequest.getProvider() != null ? careRequest.getProvider().getId() : null) // 돌봄 제공자 DTO
                .build();
    }

    // CareRequest 엔티티 리스트를 CareResponseDTO 리스트로 변환 (리스트 조회용)
    public static List<CareResponseDTO> toCareResponseDTOList(List<CareRequest> careRequests) {
        return careRequests.stream()
                .map(CareConverter::toCareResponseDTO)
                .collect(Collectors.toList());
    }


    public static EvaluationResponseDTO toEvaluationResponseDTO(Evaluation evaluation) {
        return EvaluationResponseDTO.builder()
                .evaluationId(evaluation.getEvaluationId())
                .careRequestId(evaluation.getCareRequest().getRequestId())
                .rating(evaluation.getRating())
                .feedback(evaluation.getFeedback())
                .build();
    }

    public static List<EvaluationResponseDTO> toEvaluationResponseDTOList(List<Evaluation> evaluations) {
        return evaluations.stream()
                .map(CareConverter::toEvaluationResponseDTO)
                .collect(Collectors.toList());
    }
}

