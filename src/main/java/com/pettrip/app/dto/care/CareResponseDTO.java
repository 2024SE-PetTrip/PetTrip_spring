package com.pettrip.app.dto.care;

import com.pettrip.domain.enums.CareRequestStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CareResponseDTO {

    @Getter
    @Builder
    public static class AddCareDTO {
        private Long requestId;
    }

    @Getter
    @Builder
    public static class GetCareDTO {
        private Long requestId;
        private String title;
        private Long petId;
        private String address;
        private String requestImageUrl;
    }

    @Getter
    @Builder
    public static class GetCareDetailDTO {
        private Long requestId;
        private Long requesterId;
        private Long providerId;
        private String title;
        private String address;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String requestDescription;
        private String requestImageUrl;
        private Long petId;
        private CareRequestStatus status;
    }

    @Getter
    @Builder
    public static class UpdateCareDTO {
        private Long requestId;
        private LocalDateTime updatedAt;
        private CareRequestStatus status;
    }

    @Getter
    @Builder
    public static class MatchCareProviderDTO {
        private Long requestId;
        private Long requesterId;
        private Long providerId;
        private CareRequestStatus status;
    }
}

