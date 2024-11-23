package com.pettrip.service.walk;

import com.pettrip.app.dto.walk.WalkGroupRequestDTO;
import com.pettrip.app.dto.walk.WalkGroupResponseDTO;
import com.pettrip.domain.walk.WalkGroup;

public interface WalkGroupService {
    WalkGroupResponseDTO.AddGroupDTO createWalkGroup(WalkGroupRequestDTO dto);
}
