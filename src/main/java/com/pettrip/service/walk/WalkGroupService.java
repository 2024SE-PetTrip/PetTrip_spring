package com.pettrip.service.walk;

import com.pettrip.app.dto.walk.WalkGroupRequestDTO;
import com.pettrip.app.dto.walk.WalkGroupResponseDTO;
import com.pettrip.app.dto.walk.WalkGroupUserRequestDTO;
import com.pettrip.app.dto.walk.WalkGroupUserResponseDTO;
import com.pettrip.domain.walk.WalkGroup;

import java.util.List;

public interface WalkGroupService {
    WalkGroupResponseDTO.AddGroupDTO createWalkGroup(WalkGroupRequestDTO dto);

    List<WalkGroupResponseDTO.GetGroupDTO> getAllWalkGroup();

    WalkGroupResponseDTO.GetGroupDetailDTO getWalkGroupById(Long walkGroupId);

    WalkGroupResponseDTO.GetGroupDetailFromCreatorDTO getGroupDetailFromCreator(Long walkGroupId, Long creatorId);

    List<WalkGroupResponseDTO.GetGroupDTO> filterWalkGroups(String address, List<String> tags);

    WalkGroupUserResponseDTO joinWalkGroup(WalkGroupUserRequestDTO requestDTO);

    void acceptApplicant(Long walkGroupId, Long userId);

    void rejectApplicant(Long walkGroupId, Long userId);

    void removeMember(Long walkGroupId, Long userId);
}
