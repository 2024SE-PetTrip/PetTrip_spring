package com.pettrip.app.controller;

import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.SuccessStatus;
import com.pettrip.app.dto.CareRequestDTO;
import com.pettrip.app.dto.CareResponseDTO;
import com.pettrip.domain.care.CareRequest;
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
    public ApiResponse<CareResponseDTO> addCareRequest(@RequestBody CareRequestDTO careRequestDTO) {
        CareResponseDTO careResponseDTO = careService.createCareRequest(careRequestDTO);
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_OK, careResponseDTO);
    }

    @GetMapping("/all")
    public ApiResponse<List<CareResponseDTO>> getAllCareRequests() {
        List<CareResponseDTO> allCareRequest = careService.getAllCareRequest();
        return ApiResponse.of(SuccessStatus.CARE_REQUEST_LIST_OK, allCareRequest);
    }
}
