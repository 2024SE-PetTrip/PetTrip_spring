package com.pettrip.app.dto.walk;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkGroupUserRequestDTO {
    private Long groupId;
    private Long userId;
}
