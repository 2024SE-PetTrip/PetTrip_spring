package com.pettrip.app.dto.pet;

import com.pettrip.domain.care.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PetDTO {
    private String petName;   // 펫 이름
    private Integer petAge;   // 펫 나이
    private String breed;     // 펫 품종
    private String petImageUrl; // 펫 이미지 URL

    public static PetDTO toPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setPetName(pet.getPetName());
        petDTO.setPetAge(pet.getPetAge());
        petDTO.setBreed(pet.getBreed());
        petDTO.setPetImageUrl(pet.getPetImageUrl());
        return petDTO;
    }
}
