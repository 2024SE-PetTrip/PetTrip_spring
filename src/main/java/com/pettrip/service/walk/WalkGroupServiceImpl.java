package com.pettrip.service.walk;

import com.pettrip.apiPayload.code.status.ErrorStatus;
import com.pettrip.apiPayload.exception.handler.AppHandler;
import com.pettrip.app.dto.walk.WalkGroupRequestDTO;
import com.pettrip.app.dto.walk.WalkGroupResponseDTO;
import com.pettrip.app.dto.walk.WalkGroupUserRequestDTO;
import com.pettrip.app.dto.walk.WalkGroupUserResponseDTO;
import com.pettrip.converter.CareConverter;
import com.pettrip.converter.WalkGroupConverter;
import com.pettrip.domain.User;
import com.pettrip.domain.walk.WalkGroup;
import com.pettrip.domain.walk.WalkGroupTag;
import com.pettrip.domain.walk.WalkGroupUser;
import com.pettrip.repository.UserRepository;
import com.pettrip.repository.WalkGroupRepository;
import com.pettrip.repository.WalkGroupTagRepository;
import com.pettrip.repository.WalkGroupUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalkGroupServiceImpl implements WalkGroupService {

    private final WalkGroupTagRepository walkGroupTagRepository;

    private final WalkGroupRepository walkGroupRepository;

    private final WalkGroupUserRepository walkGroupUserRepository;

    private final UserRepository userRepository;

    @Override
    public WalkGroupResponseDTO.AddGroupDTO createWalkGroup(WalkGroupRequestDTO dto) {
        User creator = userRepository.findById(dto.getCreatorId())
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));

        // 태그 ID 리스트를 엔티티 리스트로 변환
        List<WalkGroupTag> tags = dto.getTags().stream()
                .map(tagId -> walkGroupTagRepository.findById(tagId)
                        .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_WALK_GROUP_TAG)))
                .collect(Collectors.toList());

        // WalkGroup 생성 및 저장
        WalkGroup walkGroup = WalkGroupConverter.toWalkGroup(dto, tags, creator);

        walkGroupRepository.save(walkGroup);

        return WalkGroupConverter.addGroupDTO(walkGroup);
    }


    @Override
    public List<WalkGroupResponseDTO.GetGroupDTO> getAllWalkGroup() {
        List<WalkGroup> walkGroups = walkGroupRepository.findAll();

        return walkGroups.stream()
                .map(WalkGroupConverter::getGroupDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WalkGroupResponseDTO.GetGroupDetailDTO getWalkGroupById(Long walkGroupId) {
        WalkGroup walkGroup = walkGroupRepository.findById(walkGroupId)
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_WALK_GROUP));

        return WalkGroupConverter.getGroupDetailDTO(walkGroup);
    }

    public WalkGroupResponseDTO.GetGroupDetailFromCreatorDTO getGroupDetailFromCreator(Long walkGroupId, Long creatorId) {
        WalkGroup walkGroup = walkGroupRepository.findById(walkGroupId)
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_WALK_GROUP));

        if (!walkGroup.getCreator().getId().equals(creatorId)) {
            throw new AppHandler(ErrorStatus.NOT_GROUP_CREATOR);
        }

        return WalkGroupConverter.getGroupDetailFromCreatorDTO(walkGroup);
    }

    public WalkGroupUserResponseDTO joinWalkGroup(WalkGroupUserRequestDTO requestDTO) {
        WalkGroup group = walkGroupRepository.findById(requestDTO.getGroupId())
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_WALK_GROUP));
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));

        WalkGroupUser walkGroupUser = WalkGroupConverter.walkGroupUser(requestDTO, group, user);

        walkGroupUserRepository.save(walkGroupUser);

        return WalkGroupConverter.walkGroupUserResponseDTO(walkGroupUser);
    }

}

