package com.pettrip.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinDTO {
    private String realname; //실명 추가
    private String nickname;
    private String username;    // email
    private String password;
    private String userAddress;
}