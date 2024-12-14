package com.pettrip.app.dto.user;

import lombok.Data;

@Data
public class ProfileDTO {
    private String nickname;
    private String address;
    private String profileImageUrl;
}