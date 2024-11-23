package com.pettrip.app.dto.walk;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkGroupTagResponseDTO {
    private Long tagId;
    private String tagName;
}
