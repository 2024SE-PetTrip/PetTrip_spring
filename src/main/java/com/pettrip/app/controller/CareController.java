package com.pettrip.app.controller;

import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.SuccessStatus;
import com.pettrip.app.dto.care.CareRequestDTO;
import com.pettrip.app.dto.care.CareResponseDTO;
import com.pettrip.app.dto.care.EvaluationRequestDTO;
import com.pettrip.app.dto.care.EvaluationResponseDTO;
import com.pettrip.domain.enums.CareRequestStatus;
import com.pettrip.service.care.CareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/care")
@RequiredArgsConstructor
public class CareController {
    @Autowired
    private final CareService careService;

    @PostMapping("/add")
    public ApiResponse<CareResponseDTO.AddCareDTO> addCareRequest(@RequestBody CareRequestDTO careRequestDTO) {
        CareResponseDTO.AddCareDTO care = careService.createCareRequest(careRequestDTO);
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_OK, care);
    }

    @GetMapping("/all")
    public ApiResponse<List<CareResponseDTO.GetCareDTO>> getAllCareRequests() {
        List<CareResponseDTO.GetCareDTO> allCareRequest = careService.getAllCareRequest();
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_LIST_OK, allCareRequest);
    }

    // Care 요청 상세 조회
    @GetMapping("/{requestId}")
    public ApiResponse<CareResponseDTO.GetCareDetailDTO> getCareRequestById(@PathVariable("requestId") Long requestId) {
        CareResponseDTO.GetCareDetailDTO careResponseDTO = careService.getCareRequestById(requestId);
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_DETAIL_OK, careResponseDTO);
    }

    @GetMapping("/users/{userId}")
    public ApiResponse<List<CareResponseDTO.GetCareDTO>> getCareRequestsByUserId(@PathVariable("userId") Long userId) {
        List<CareResponseDTO.GetCareDTO> CareRequestsByUserId = careService.getCareRequestsByRequesterId(userId);

        return ApiResponse.of(SuccessStatus.CARE_REQUEST_LIST_OK, CareRequestsByUserId);
    }

    @GetMapping("/status")
    public ApiResponse<List<CareResponseDTO.GetCareDTO>> getCareRequestsByStatus(
            @RequestParam(value = "status", required = false) CareRequestStatus status) {

        List<CareResponseDTO.GetCareDTO> careRequestsByStatus;

        if (status == null) {
            careRequestsByStatus = careService.getAllCareRequest();
        } else {
            careRequestsByStatus = careService.getCareRequestsByStatus(status);
        }

        return ApiResponse.of(SuccessStatus.CARE_REQUEST_LIST_OK, careRequestsByStatus);
    }


    @PutMapping("/{requestId}/update")
    public ApiResponse<CareResponseDTO.UpdateCareDTO> updateCareRequest(
            @PathVariable("requestId") Long requestId,
            @RequestBody CareRequestDTO careRequestDTO) {

        CareResponseDTO.UpdateCareDTO updatedCareRequest = careService.updateCareRequest(requestId, careRequestDTO);

        return ApiResponse.of(SuccessStatus.CARE_REQUEST_UPDATED, updatedCareRequest);
    }

    @DeleteMapping("/{requestId}/delete")
    public ApiResponse<Void> deleteCareRequest(@PathVariable("requestId") Long requestId) {
        careService.deleteCareRequest(requestId);
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_DELETED, null);
    }

    @PutMapping("/{requestId}/match")
    public ApiResponse<CareResponseDTO.MatchCareProviderDTO> matchCareProvider(
            @PathVariable("requestId") Long requestId,
            @RequestBody Map<String, Long> requestBody) {

        Long providerId = requestBody.get("providerId");
        CareResponseDTO.MatchCareProviderDTO careResponseDTO = careService.matchCareProvider(requestId, providerId);
        return ApiResponse.of(SuccessStatus.CARE_PROVIDER_MATCHED, careResponseDTO);
    }

    @PostMapping("/evaluation")
    public ApiResponse<EvaluationResponseDTO> evaluateCareProvider(
            @RequestBody EvaluationRequestDTO requestDto) {
        EvaluationResponseDTO evaluationResponseDto = careService.addEvaluation(requestDto);
        return ApiResponse.of(SuccessStatus.CARE_EVALUATION_OK, evaluationResponseDto);
    }

    @GetMapping("/evaluation/{providerId}")
    public ApiResponse<List<EvaluationResponseDTO>> getAllEvaluationByProvider(
            @PathVariable Long providerId) {

        List<EvaluationResponseDTO> allEvaluation = careService.getEvaluationsByUserId(providerId);

        return ApiResponse.of(SuccessStatus.CARE_EVALUATION_LIST_OK, allEvaluation);
    }
}
