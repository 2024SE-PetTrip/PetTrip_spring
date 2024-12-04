package com.pettrip.service.walk;

import com.pettrip.apiPayload.code.status.ErrorStatus;
import com.pettrip.apiPayload.exception.handler.AppHandler;
import com.pettrip.app.dto.walk.WalkGroupRequestDTO;
import com.pettrip.app.dto.walk.WalkGroupResponseDTO;
import com.pettrip.app.dto.walk.WalkGroupUserRequestDTO;
import com.pettrip.app.dto.walk.WalkGroupUserResponseDTO;
import com.pettrip.converter.WalkGroupConverter;
import com.pettrip.domain.User;
import com.pettrip.domain.course.Course;
import com.pettrip.domain.walk.WalkGroup;
import com.pettrip.domain.walk.WalkGroupTag;
import com.pettrip.domain.walk.WalkGroupUser;
import com.pettrip.repository.*;
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
    private final CourseRepository courseRepository;

    @Override
    public WalkGroupResponseDTO.AddGroupDTO createWalkGroup(WalkGroupRequestDTO dto) {
        User creator = userRepository.findById(dto.getCreatorId())
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_COURSE));

        // 태그 name 리스트를 엔티티 리스트로 변환
        List<WalkGroupTag> tags = dto.getTags().stream()
                .map(tagName -> walkGroupTagRepository.findByName(tagName)
                        .orElseGet(() -> {
                            WalkGroupTag newTag = new WalkGroupTag();
                            newTag.setName(tagName);
                            return walkGroupTagRepository.save(newTag);
                        }))
                .collect(Collectors.toList());

        // WalkGroup 생성 및 저장
        WalkGroup walkGroup = WalkGroupConverter.toWalkGroup(dto, course, tags, creator);

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

    public List<WalkGroupResponseDTO.GetGroupDTO> filterWalkGroups(String address, List<String> tags) {
        List<WalkGroup> walkGroups = walkGroupRepository.findByAddressAndAnyTags(address, tags);

        return walkGroups.stream()
                .map(WalkGroupConverter::getGroupDTO)
                .collect(Collectors.toList());
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

    @Override
    public void acceptApplicant(Long walkGroupId, Long userId) {
        WalkGroupUser applicant = walkGroupUserRepository.findByGroup_GroupIdAndUser_IdAndIsApproved(walkGroupId, userId, false)
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_WALK_GROUP_APPLICANTS));
        applicant.setApproved(true);
        walkGroupUserRepository.save(applicant);
    }

    @Override
    public void rejectApplicant(Long walkGroupId, Long userId) {
        WalkGroupUser applicant = walkGroupUserRepository.findByGroup_GroupIdAndUser_IdAndIsApproved(walkGroupId, userId, false)
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_WALK_GROUP_APPLICANTS));
        walkGroupUserRepository.delete(applicant);
    }

    @Override
    public void removeMember(Long walkGroupId, Long userId) {
        WalkGroupUser member = walkGroupUserRepository.findByGroup_GroupIdAndUser_IdAndIsApproved(walkGroupId, userId, true)
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_WALK_GROUP_MEMBER));
        walkGroupUserRepository.delete(member); // 멤버 삭제
    }

}

