package com.pettrip.app.dto.user;


import com.pettrip.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    private String username;    //이메일임.
    private String password;
    private User.Role role;

    public User.Role getRole() {
        return role;
    }



}