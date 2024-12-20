package com.pettrip.app.controller;

import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.SuccessStatus;
import com.pettrip.app.dto.walk.WalkGroupRequestDTO;
import com.pettrip.app.dto.walk.WalkGroupResponseDTO;
import com.pettrip.app.dto.walk.WalkGroupUserRequestDTO;
import com.pettrip.app.dto.walk.WalkGroupUserResponseDTO;
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
    public ApiResponse<WalkGroupResponseDTO.AddGroupDTO> addWalkGroup(@RequestBody WalkGroupRequestDTO walkGroupRequestDTO) {
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

    @GetMapping("/{walkGroupId}/creator-detail")
    public ApiResponse<WalkGroupResponseDTO.GetGroupDetailFromCreatorDTO> getGroupDetailFromCreator(
            @PathVariable Long walkGroupId,
            @RequestParam Long creatorId) {
        WalkGroupResponseDTO.GetGroupDetailFromCreatorDTO walkGroupById =
                walkGroupService.getGroupDetailFromCreator(walkGroupId, creatorId);

        return ApiResponse.of(SuccessStatus.WALK_GROUP_CREATOR_DETAIL_OK, walkGroupById);
    }

    @GetMapping("/filter")
    public ApiResponse<List<WalkGroupResponseDTO.GetGroupDTO>> filterWalkGroups(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) List<String> tags) {

        List<WalkGroupResponseDTO.GetGroupDTO> walkGroups;

        if ((address == null || address.isEmpty()) && (tags == null || tags.isEmpty())) {
            walkGroups = walkGroupService.getAllWalkGroup();
        } else {
            walkGroups = walkGroupService.filterWalkGroups(address, tags);
        }

        return ApiResponse.of(SuccessStatus.WALK_GROUP_LIST_OK, walkGroups);
    }

    @PostMapping("/join")
    public ApiResponse<WalkGroupUserResponseDTO> joinWalkGroup(
            @RequestBody WalkGroupUserRequestDTO requestDTO) {
        WalkGroupUserResponseDTO walkGroupUserResponseDTO = walkGroupService.joinWalkGroup(requestDTO);

        return ApiResponse.of(SuccessStatus.WALK_GROUP_JOIN_REQUEST_OK, walkGroupUserResponseDTO);
    }

    // 참가 신청자 수락
    @PostMapping("/{walkGroupId}/creator-detail/applicants/{userId}/accept")
    public ApiResponse<String> acceptApplicant(@PathVariable Long walkGroupId, @PathVariable Long userId) {
        walkGroupService.acceptApplicant(walkGroupId, userId);
        return ApiResponse.of(SuccessStatus.WALK_GROUP_APPLICANT_ACCEPTED, null);
    }

    // 참가 신청자 거절
    @DeleteMapping("/{walkGroupId}/creator-detail/applicants/{userId}/reject")
    public ApiResponse<String> rejectApplicant(@PathVariable Long walkGroupId, @PathVariable Long userId) {
        walkGroupService.rejectApplicant(walkGroupId, userId);
        return ApiResponse.of(SuccessStatus.WALK_GROUP_APPLICANT_REJECTED, null);
    }

    // 멤버 삭제
    @DeleteMapping("/{walkGroupId}/creator-detail/members/{userId}/delete")
    public ApiResponse<String> removeMember(@PathVariable Long walkGroupId, @PathVariable Long userId) {
        walkGroupService.removeMember(walkGroupId, userId);
        return ApiResponse.of(SuccessStatus.WALK_GROUP_MEMBER_REMOVED, null);
    }

}
