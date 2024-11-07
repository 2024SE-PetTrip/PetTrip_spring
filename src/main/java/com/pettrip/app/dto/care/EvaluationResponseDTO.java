package com.pettrip.app.dto.care;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationResponseDTO {
    private Long evaluationId;

    private Long careRequestId;

    private Integer rating;

    private String feedback;
}
