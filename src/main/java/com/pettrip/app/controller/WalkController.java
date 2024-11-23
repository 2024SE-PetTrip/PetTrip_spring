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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/all")
    public ApiResponse<List<WalkGroupResponseDTO.GetGroupDTO>> getAllWalkGroup() {
        List<WalkGroupResponseDTO.GetGroupDTO> allWalkGroup = walkGroupService.getAllWalkGroup();
        return ApiResponse.of(SuccessStatus.WALK_GROUP_LIST_OK, allWalkGroup);
    }

    @GetMapping("/{walkGroupId}")
    public ApiResponse<WalkGroupResponseDTO.GetGroupDetailDTO> getWalkGroupById(@PathVariable("walkGroupId") Long walkGroupId) {
        WalkGroupResponseDTO.GetGroupDetailDTO walkGroupById = walkGroupService.getWalkGroupById(walkGroupId);
        return ApiResponse.of(SuccessStatus.WALK_GROUP_DETAIL_OK, walkGroupById);
    }

}
