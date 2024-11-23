package com.pettrip.service.walk;

import com.pettrip.app.dto.walk.WalkGroupRequestDTO;
import com.pettrip.app.dto.walk.WalkGroupResponseDTO;
import com.pettrip.domain.walk.WalkGroup;

import java.util.List;

public interface WalkGroupService {
    WalkGroupResponseDTO.AddGroupDTO createWalkGroup(WalkGroupRequestDTO dto);

    List<WalkGroupResponseDTO.GetGroupDTO> getAllWalkGroup();

    WalkGroupResponseDTO.GetGroupDetailDTO getWalkGroupById(Long walkGroupId);

    WalkGroupResponseDTO.GetGroupDetailFromCreatorDTO getGroupDetailFromCreator(Long walkGroupId, Long creatorId);
}
