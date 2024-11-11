package com.pettrip.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.StatusResponse;
import com.pettrip.app.dto.user.JoinDTO;
import com.pettrip.service.user.JoinService;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/user")
public class JoinController {

    private final JoinService joinService;
    @PostMapping("/join")
    public ApiResponse<String> joinProcess(@RequestBody JoinDTO joinDTO) {
        StatusResponse statusResponse = joinService.joinProcess(joinDTO);
        if(statusResponse.isSuccess()) {
            return ApiResponse.of(statusResponse.getSuccessStatus(), "");
        } else {
            return ApiResponse.errorof(statusResponse.getErrorStatus(), "");
        }
    }

}