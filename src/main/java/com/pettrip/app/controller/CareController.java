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
    public ApiResponse<CareResponseDTO> addCareRequest(@RequestBody CareRequestDTO careRequestDTO) {
        CareResponseDTO careResponseDTO = careService.createCareRequest(careRequestDTO);
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_OK, careResponseDTO);
    }

    @GetMapping("/all")
    public ApiResponse<List<CareResponseDTO>> getAllCareRequests() {
        List<CareResponseDTO> allCareRequest = careService.getAllCareRequest();
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_LIST_OK, allCareRequest);
    }

    @GetMapping("/{requestId}")
    public ApiResponse<CareResponseDTO> getCareRequestById(@PathVariable("requestId") Long requestId) {
        CareResponseDTO careResponseDTO = careService.getCareRequestById(requestId);
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_DETAIL_OK, careResponseDTO);
    }

    @GetMapping("/users/{userId}")
    public ApiResponse<List<CareResponseDTO>> getCareRequestsByUserId(@PathVariable("userId") Long userId) {
        List<CareResponseDTO> CareRequestsByUserId = careService.getCareRequestsByRequesterId(userId);

        return ApiResponse.of(SuccessStatus.CARE_REQUEST_LIST_OK, CareRequestsByUserId);
    }

    @GetMapping("/status")
    public ApiResponse<List<CareResponseDTO>> getCareRequestsByStatus(
            @RequestParam(value = "status", required = false) CareRequestStatus status) {

        List<CareResponseDTO> careRequestsByStatus;

        if (status == null) {
            careRequestsByStatus = careService.getAllCareRequest();
        } else {
            careRequestsByStatus = careService.getCareRequestsByStatus(status);
        }

        return ApiResponse.of(SuccessStatus.CARE_REQUEST_LIST_OK, careRequestsByStatus);
    }


    @PutMapping("/{requestId}/update")
    public ApiResponse<CareResponseDTO> updateCareRequest(
            @PathVariable("requestId") Long requestId,
            @RequestBody CareRequestDTO careRequestDTO) {

        CareResponseDTO updatedCareRequest = careService.updateCareRequest(requestId, careRequestDTO);

        return ApiResponse.of(SuccessStatus.CARE_REQUEST_UPDATED, updatedCareRequest);
    }

    @DeleteMapping("/{requestId}/delete")
    public ApiResponse<Void> deleteCareRequest(@PathVariable("requestId") Long requestId) {
        careService.deleteCareRequest(requestId);
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_DELETED, null);
    }

    @PutMapping("/{requestId}/match")
    public ApiResponse<CareResponseDTO> matchCareProvider(
            @PathVariable("requestId") Long requestId,
            @RequestBody Map<String, Long> requestBody) {

        Long providerId = requestBody.get("providerId");
        CareResponseDTO careResponseDTO = careService.matchCareProvider(requestId, providerId);
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
