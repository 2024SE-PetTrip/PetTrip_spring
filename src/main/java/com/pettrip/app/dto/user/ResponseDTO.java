package com.pettrip.app.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO {
    private boolean isSuccess;
    private int code;
    private String message;
}