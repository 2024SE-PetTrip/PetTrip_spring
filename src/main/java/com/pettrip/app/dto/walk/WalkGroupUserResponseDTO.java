package com.pettrip.app.dto.walk;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkGroupUserResponseDTO {
    private Long groupId;
    private Long userId;
    private boolean isApproved;
}

