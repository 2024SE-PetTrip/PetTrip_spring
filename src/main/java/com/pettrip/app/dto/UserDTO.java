package com.pettrip.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    private String userAddress;

    public static UserDTO toUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserAddress(user.getUserAddress());
        userDTO.setNickname(user.getNickname());
        userDTO.setRealname(user.getRealname()); //실명 추가
        return userDTO;
    }
}
