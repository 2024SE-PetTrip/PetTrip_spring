package com.pettrip.app.dto;

import com.pettrip.domain.User;
import com.pettrip.domain.enums.CareRequestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CareRequestDTO {

    private Long userId;

    private String requestDescription;

    private CareRequestStatus status;

    private String requestImageUrl;
}
