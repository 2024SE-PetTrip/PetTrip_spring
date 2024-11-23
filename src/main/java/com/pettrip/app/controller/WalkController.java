package com.pettrip.app.controller;

import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.SuccessStatus;
import com.pettrip.app.dto.care.CareRequestDTO;
import com.pettrip.app.dto.care.CareResponseDTO;
import com.pettrip.app.dto.walk.WalkGroupRequestDTO;
import com.pettrip.app.dto.walk.WalkGroupResponseDTO;
import com.pettrip.service.walk.WalkGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/walk")
@RequiredArgsConstructor
public class WalkController {
    @Autowired
    private final WalkGroupService walkGroupService;

    @PostMapping("/add")
    public ApiResponse<WalkGroupResponseDTO.AddGroupDTO> addCareRequest(@RequestBody WalkGroupRequestDTO walkGroupRequestDTO) {
        WalkGroupResponseDTO.AddGroupDTO walkGroup = walkGroupService.createWalkGroup(walkGroupRequestDTO);
        return ApiResponse.of(SuccessStatus.WALK_GROUP_REQUEST_OK, walkGroup);
    }
}
