package com.pettrip.converter;

import com.pettrip.app.dto.care.CareRequestDTO;
import com.pettrip.app.dto.care.CareResponseDTO;
import com.pettrip.app.dto.care.ChatMessageDTO;
import com.pettrip.app.dto.care.EvaluationResponseDTO;
import com.pettrip.domain.care.CareRequest;
import com.pettrip.domain.care.ChatMessage;
import com.pettrip.domain.care.Evaluation;
import com.pettrip.domain.enums.CareRequestStatus;

import javax.persistence.Convert;
import java.util.List;
import java.util.stream.Collectors;

@Convert
public class CareConverter {

    public static CareRequest toCareRequest(CareRequestDTO dto) {
        return CareRequest.builder()
                .title(dto.getTitle())
                .address(dto.getAddress())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .requestDescription(dto.getRequestDescription())
                .requestImageUrl(dto.getRequestImageUrl())
                .status(CareRequestStatus.PENDING)
                .build();
    }

    // CareRequest 추가 응답 dto
    public static CareResponseDTO.AddCareDTO addCareDTO(CareRequest careRequest) {
        return CareResponseDTO.AddCareDTO.builder()
                .requestId(careRequest.getRequestId())
                .build();
    }

    // CareRequest 조회 응답 dto
    public static CareResponseDTO.GetCareDTO getCareDTO(CareRequest careRequest) {
        return CareResponseDTO.GetCareDTO.builder()
                .requestId(careRequest.getRequestId())
                .build();
    }

    // CareRequest 상세 조회 응답 dto
    public static CareResponseDTO.GetCareDetailDTO getCareDetailDTO(CareRequest careRequest) {
        return CareResponseDTO.GetCareDetailDTO.builder()
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

    // CareRequest 업데이트 응답 dto
    public static CareResponseDTO.UpdateCareDTO updateCareDTO(CareRequest careRequest) {
        return CareResponseDTO.UpdateCareDTO.builder()
                .requestId(careRequest.getRequestId())
                .updatedAt(careRequest.getUpdatedAt())
                .status(careRequest.getStatus())
                .build();
    }

    // CareRequest 매칭 응답 dto
    public static CareResponseDTO.MatchCareProviderDTO matchCareProviderDTO(CareRequest careRequest) {
        return CareResponseDTO.MatchCareProviderDTO.builder()
                .requestId(careRequest.getRequestId())
                .requesterId(careRequest.getRequester().getId())
                .providerId(careRequest.getProvider().getId())
                .status(careRequest.getStatus())
                .build();
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

    public static ChatMessage toChatMessage(ChatMessageDTO dto) {
        return ChatMessage.builder()
                .roomId(dto.getRoomId())
                .authorId(dto.getAuthorId())
                .message(dto.getMessage())
                .build();
    }
}

