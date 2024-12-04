package com.pettrip.app.controller;

import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.SuccessStatus;
import com.pettrip.app.dto.pet.PetDTO;
import com.pettrip.service.user.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    // 반려동물 인증
    @PostMapping("/validate")
    public ApiResponse<Boolean> validatePet(
            @RequestParam String rfid,
            @RequestParam String ownerName
    ) {
        boolean isValid = petService.validatePet(rfid, ownerName);
        return ApiResponse.of(SuccessStatus.VALIDATION_SUCCESS, isValid);
    }

    // 반려동물 추가
    @PostMapping("/add")
    public ApiResponse<Void> addPet(@RequestBody PetDTO petDTO) {
        petService.addPet(petDTO);
        return ApiResponse.of(SuccessStatus.PET_ADDED_OK, null);
    }
}
