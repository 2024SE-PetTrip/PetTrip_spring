package com.pettrip.app.dto.pet;

import lombok.Data;

@Data
public class PetDTO {
    private Long userId; // 사용자 ID
    private String petName; // 반려동물 이름
    private Integer petAge; // 반려동물 나이
    private String breed; // 품종
    private String petImageUrl; // 이미지 URL
    private Boolean validated; // 인증 여부 (Optional)
}