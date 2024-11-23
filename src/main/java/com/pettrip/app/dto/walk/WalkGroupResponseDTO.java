package com.pettrip.app.dto.walk;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class WalkGroupResponseDTO {

    @Getter
    @Builder
    public static class AddGroupDTO {
        private Long groupId;
    }

    @Getter
    @Builder
    public static class GetGroupDTO {
        private Long groupId;
        private String groupName;
        private Integer maxParticipants;
        private List<WalkGroupTagResponseDTO> tags;
    }

    @Getter
    @Builder
    public static class GetGroupDetailDTO {
        private Long groupId;
        private String groupName;
        private Long courseId;
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalDate walkingDate;
        private Integer maxParticipants;
        private Integer maxPetsPerUser;
        private String groupDescription;
        private String groupAddress;
        private List<WalkGroupTagResponseDTO> tags;
    }

    @Getter
    @Builder
    public static class GetGroupDetailFromCreatorDTO {
        private Long groupId;
        private Long courseId;
        private LocalDate walkingDate;
        private String groupDescription;
        private Long creatorId;
        private List<MemberDTO> members;
    }

    @Getter
    @Builder
    public static class UpdateGroupDTO {
        private Long groupId;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Builder
    public static class MemberDTO {
        private Long userId;
        private boolean isApproved;
    }
}

