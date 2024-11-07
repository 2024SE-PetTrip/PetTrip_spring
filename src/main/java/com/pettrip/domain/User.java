package com.pettrip.domain;

import com.pettrip.domain.common.BaseEntity;
import lombok.*;
import com.pettrip.app.dto.JoinDTO;
import com.pettrip.app.dto.LoginDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) // dbms
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "user_id")
    private Long id;

    //실명 추가
    @Column(nullable = false, length = 20)
    private String realname;

    @Column(nullable = false, length = 20)
    private String nickname;

    //아이디이자 이메일
    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(length = 255)
    private String profileImageUrl;

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<UserPloggingGroupApplyment> //userPloggingGroupApplyments;

    public static User toUser(JoinDTO joinDTO) {
        User user = new User();
        user.setRealname(joinDTO.getRealname());
        user.setNickname(joinDTO.getNickname());
        user.setUsername(joinDTO.getUsername());
        user.setPassword(joinDTO.getPassword());
        user.setAddress(joinDTO.getUserAddress());
        return user;
    }

    public static User toUser(LoginDTO loginDTO) {
        User user = new User();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(loginDTO.getPassword());
        return user;
    }
}