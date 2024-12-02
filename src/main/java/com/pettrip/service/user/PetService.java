package com.pettrip.service.user;

import com.pettrip.app.dto.pet.PetDTO;
import com.pettrip.domain.Pet;
import com.pettrip.domain.User;
import com.pettrip.repository.PetRepository;
import com.pettrip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * RFID 인증
     * 외부 API 호출 없이 단순히 성공 응답 반환
     */
    public boolean validatePet(String rfid, String ownerName) {
        // 외부 API 호출 대신 기본 성공 처리
        System.out.println("RFID: " + rfid + ", Owner Name: " + ownerName);
        return true; // 항상 성공으로 반환
    }

    /**
     * 반려동물 추가
     */
    public void addPet(PetDTO petDTO) {
        User user = userRepository.findById(petDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        Pet pet = new Pet();
        pet.setUser(user);
        pet.setPetName(petDTO.getPetName());
        pet.setPetAge(petDTO.getPetAge());
        pet.setBreed(petDTO.getBreed());
        pet.setPetImageUrl(petDTO.getPetImageUrl());
        pet.setValidated(petDTO.getValidated() != null && petDTO.getValidated()); // 기본값 false 처리

        petRepository.save(pet);
    }
}