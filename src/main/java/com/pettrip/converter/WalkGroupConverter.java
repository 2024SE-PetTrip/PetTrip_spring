package com.pettrip.converter;

import com.pettrip.app.dto.walk.*;
import com.pettrip.domain.User;
import com.pettrip.domain.course.Course;
import com.pettrip.domain.walk.WalkGroup;
import com.pettrip.domain.walk.WalkGroupTag;
import com.pettrip.domain.walk.WalkGroupUser;

import java.util.List;
import java.util.stream.Collectors;

public class WalkGroupConverter {

    public static WalkGroup toWalkGroup(WalkGroupRequestDTO dto, Course course, List<WalkGroupTag> tags, User creator) {
        return WalkGroup.builder()
                .groupName(dto.getGroupName())
                .course(course)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .walkingDate(dto.getWalkingDate())
                .maxParticipants(dto.getMaxParticipants())
                .maxPetsPerUser(dto.getMaxPetsPerUser())
                .groupDescription(dto.getGroupDescription())
                .groupAddress(dto.getGroupAddress())
                .tags(tags)
                .creator(creator)
                .build();
    }

    public static WalkGroupResponseDTO.AddGroupDTO addGroupDTO(WalkGroup walkGroup) {
        return WalkGroupResponseDTO.AddGroupDTO.builder()
                .groupId(walkGroup.getGroupId())
                .build();
    }

    public static WalkGroupResponseDTO.GetGroupDTO getGroupDTO(WalkGroup walkGroup) {
        return WalkGroupResponseDTO.GetGroupDTO.builder()
                .groupId(walkGroup.getGroupId())
                .groupName(walkGroup.getGroupName())
                .maxParticipants(walkGroup.getMaxParticipants())
                .tags(walkGroup.getTags().stream()
                        .map(WalkGroupConverter::walkGroupTagResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static WalkGroupResponseDTO.GetGroupDetailDTO getGroupDetailDTO(WalkGroup walkGroup) {
        return WalkGroupResponseDTO.GetGroupDetailDTO.builder()
                .groupId(walkGroup.getGroupId())
                .groupName(walkGroup.getGroupName())
                .courseId(walkGroup.getCourse().getCourseId())
                .startDate(walkGroup.getStartDate())
                .endDate(walkGroup.getEndDate())
                .walkingDate(walkGroup.getWalkingDate())
                .maxParticipants(walkGroup.getMaxParticipants())
                .maxPetsPerUser(walkGroup.getMaxPetsPerUser())
                .groupDescription(walkGroup.getGroupDescription())
                .groupAddress(walkGroup.getGroupAddress())
                .tags(walkGroup.getTags().stream()
                        .map(WalkGroupConverter::walkGroupTagResponseDTO)
                        .collect(Collectors.toList()))
                .members(walkGroup.getMembers().stream()
                        .map(WalkGroupConverter::memberDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static WalkGroupResponseDTO.GetGroupDetailFromCreatorDTO getGroupDetailFromCreatorDTO(WalkGroup walkGroup) {
        return WalkGroupResponseDTO.GetGroupDetailFromCreatorDTO.builder()
                .groupId(walkGroup.getGroupId())
                .courseId(walkGroup.getCourse().getCourseId())
                .walkingDate(walkGroup.getWalkingDate())
                .groupDescription(walkGroup.getGroupDescription())
                .creatorId(walkGroup.getCreator().getId())
                .members(walkGroup.getMembers().stream()
                        .map(WalkGroupConverter::memberDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static WalkGroupResponseDTO.MemberDTO memberDTO(WalkGroupUser walkGroupUser) {
        return WalkGroupResponseDTO.MemberDTO.builder()
                .userId(walkGroupUser.getUser().getId())
                .isApproved(walkGroupUser.isApproved())
                .build();
    }

    public static WalkGroupTagResponseDTO walkGroupTagResponseDTO(WalkGroupTag tag) {
        return WalkGroupTagResponseDTO.builder()
                .tagId(tag.getId())
                .tagName(tag.getName())
                .build();
    }

    // 산책 모임 참가 신청 요청 dto -> 엔티티
    public static WalkGroupUser walkGroupUser(WalkGroupUserRequestDTO dto, WalkGroup group, User user) {
        return WalkGroupUser.builder()
                .group(group)
                .user(user)
                .isApproved(false) // 초기 상태는 승인되지 않은 상태
                .build();
    }

    public static WalkGroupUserResponseDTO walkGroupUserResponseDTO(WalkGroupUser walkGroupUser) {
        return WalkGroupUserResponseDTO.builder()
                .groupId(walkGroupUser.getGroup().getGroupId())
                .userId(walkGroupUser.getUser().getId())
                .isApproved(walkGroupUser.isApproved())
                .build();
    }
}
