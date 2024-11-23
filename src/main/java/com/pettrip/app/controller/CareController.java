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

    @GetMapping("/filter")
    public ApiResponse<List<CareResponseDTO.GetCareDTO>> getCareRequestsByFilter(
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "breed", required = false) String breed) {

        List<CareResponseDTO.GetCareDTO> careRequests = careService.getCareRequestsByFilter(address, breed);
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_LIST_OK, careRequests);
    }

    @PutMapping("/{requestId}/update")
    public ApiResponse<CareResponseDTO.UpdateCareDTO> updateCareRequest(
            @PathVariable("requestId") Long requestId,
            @RequestBody CareRequestDTO requestDto) {
        CareResponseDTO.UpdateCareDTO updateCareDTO = careService.updateCareRequest(requestId, requestDto);
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_UPDATED, updateCareDTO);
    }

    @DeleteMapping("/{requestId}/delete")
    public ApiResponse<Void> deleteCareRequest(@PathVariable("requestId") Long requestId) {
        careService.deleteCareRequest(requestId);
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_DELETED, null);
    }

    @PutMapping("/{requestId}/match/{providerId}")
    public ApiResponse<CareResponseDTO.MatchCareProviderDTO> matchCareProvider(
            @PathVariable("requestId") Long requestId,
            @PathVariable("providerId") Long providerId) {
        CareResponseDTO.MatchCareProviderDTO careResponseDTO = careService.matchCareProvider(requestId, providerId);
        return ApiResponse.of(SuccessStatus.CARE_PROVIDER_MATCHED, careResponseDTO);
    }

    @PutMapping("/{requestId}/complete")
    public ApiResponse<CareResponseDTO.UpdateCareDTO> completeCareRequest(@PathVariable("requestId") Long requestId) {
        CareResponseDTO.UpdateCareDTO updatedCareRequest = careService.updateCareRequestAsCompleted(requestId);
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_COMPLETED, updatedCareRequest);
    }

    @PostMapping("/{requestId}/evaluate")
    public ApiResponse<EvaluationResponseDTO> evaluateCareProvider(
            @PathVariable("requestId") Long requestId,
            @RequestBody EvaluationRequestDTO requestDto) {
        EvaluationResponseDTO evaluationResponseDto = careService.addEvaluation(requestId, requestDto);
        return ApiResponse.of(SuccessStatus.CARE_EVALUATION_OK, evaluationResponseDto);
    }

    @GetMapping("/evaluate/{userId}")
    public ApiResponse<List<EvaluationResponseDTO>> getEvaluationsByUser(
            @PathVariable Long userId) {

        List<EvaluationResponseDTO> evaluations = careService.getEvaluationsByUserId(userId);

        return ApiResponse.of(SuccessStatus.CARE_EVALUATION_LIST_OK, evaluations);
    }

}
