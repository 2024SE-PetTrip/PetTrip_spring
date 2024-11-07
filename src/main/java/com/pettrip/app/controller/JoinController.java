package com.pettrip.app.controller;

import io.swagger.models.Response;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.ErrorStatus;
import com.pettrip.apiPayload.code.status.StatusResponse;
import com.pettrip.apiPayload.code.status.SuccessStatus;
import com.pettrip.app.dto.JoinDTO;
import com.pettrip.app.dto.ResponseDTO;
import com.pettrip.service.JoinService;

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