package com.pettrip.app.dto.care;

import com.pettrip.app.dto.UserDTO;
import com.pettrip.domain.enums.CareRequestStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CareResponseDTO {

    private Long requestId;

    private Long requesterId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String requestDescription;

    private String requestImageUrl;

    private CareRequestStatus status;

    private Long providerId;
}

