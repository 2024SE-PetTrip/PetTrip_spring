package com.pettrip.converter;

import com.pettrip.app.dto.CareRequestDTO;
import com.pettrip.app.dto.CareResponseDTO;
import com.pettrip.domain.care.CareRequest;
import com.pettrip.domain.enums.CareRequestStatus;

import javax.persistence.Convert;
import java.util.List;
import java.util.stream.Collectors;

@Convert
public class CareRequestConverter {


    // CareRequest 엔티티를 CareResponseDTO로 변환 (상세 정보)
    public static CareResponseDTO toCareResponseDTO(CareRequest careRequest) {
        return CareResponseDTO.builder()
                .careId(careRequest.getRequestId())
                .userId(careRequest.getUser().getId())
                .requestDate(careRequest.getRequestDate())
                .requestDescription(careRequest.getRequestDescription())
                .status(careRequest.getStatus())
                .requestImageUrl(careRequest.getRequestImageUrl())
                .build();
    }

    // CareRequest 엔티티 리스트를 CareResponseDTO 리스트로 변환 (리스트 조회용)
    public static List<CareResponseDTO> toCareResponseDTOList(List<CareRequest> careRequests) {
        return careRequests.stream()
                .map(CareRequestConverter::toCareResponseDTO)
                .collect(Collectors.toList());
    }

    public static CareRequest toCareRequest(CareRequestDTO dto) {
        CareRequest careRequest = new CareRequest();

        careRequest.setRequestDescription(dto.getRequestDescription());
        careRequest.setStatus(dto.getStatus() != null ? dto.getStatus() : CareRequestStatus.PENDING);
        careRequest.setRequestImageUrl(dto.getRequestImageUrl());

        return careRequest;
    }
}

