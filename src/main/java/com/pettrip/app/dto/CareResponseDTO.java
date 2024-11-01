package com.pettrip.app.dto;

import com.pettrip.domain.User;
import com.pettrip.domain.enums.CareRequestStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CareResponseDTO {

    private Long careId;

    private Long userId;

    private LocalDateTime requestDate;

    private String requestDescription;

    private CareRequestStatus status;

    private String requestImageUrl;
}

