package com.pettrip.app.dto.care;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CareRequestDTO {

    private Long requesterId;

    private String title;

    private String address;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String requestDescription;

    private String requestImageUrl;

    private Long petId;

}
