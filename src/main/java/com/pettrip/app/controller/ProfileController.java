package com.pettrip.app.controller;

import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.ErrorStatus;
import com.pettrip.apiPayload.code.status.SuccessStatus;
import com.pettrip.app.dto.user.ProfileDTO;
import com.pettrip.service.user.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController("customProfileController")
@RequiredArgsConstructor
@RequestMapping("/user")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profile/{id}")
    public ApiResponse<ProfileDTO> getProfile(@PathVariable Long id) {
        // ProfileService에서 ProfileDTO를 가져옴
        ProfileDTO profileDTO = profileService.getProfileById(id);

        // ProfileDTO가 null인 경우 에러 응답 반환
        if (profileDTO == null) {
            return ApiResponse.errorof(ErrorStatus.PROFILE_NOT_FOUND, null);
        }

        // 성공 응답 반환
        return ApiResponse.of(SuccessStatus.PROFILE_LOAD_OK, profileDTO);
    }
}