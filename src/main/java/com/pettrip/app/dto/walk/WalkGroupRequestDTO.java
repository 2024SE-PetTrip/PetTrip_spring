package com.pettrip.app.dto.walk;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkGroupRequestDTO {
    private Long creatorId;
    private String groupName;
    private Long courseId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate walkingDate;
    private Integer maxParticipants;
    private Integer maxPetsPerUser;
    private String groupDescription;
    private String groupAddress;
    private List<Long> tags;
}
