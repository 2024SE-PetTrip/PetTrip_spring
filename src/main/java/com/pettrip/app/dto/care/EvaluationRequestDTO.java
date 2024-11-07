package com.pettrip.app.dto.care;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationRequestDTO {

    private Long careRequestId;

    private Integer rating;

    private String feedback;
}
