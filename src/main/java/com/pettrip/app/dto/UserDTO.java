package com.pettrip.app.dto;

import lombok.*;
import com.pettrip.domain.User;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String realname; //실명 추가
    private String nickname;
    private String username;
    private String password;
    private String address;
    private String profileImageUrl;

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRealname(user.getRealname());
        userDTO.setNickname(user.getNickname());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setAddress(user.getAddress());
        userDTO.setProfileImageUrl(user.getProfileImageUrl());

        return userDTO;
    }
}
