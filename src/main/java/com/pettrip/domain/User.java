package com.pettrip.domain;

import lombok.*;
import com.pettrip.app.dto.user.JoinDTO;
import com.pettrip.app.dto.user.LoginDTO;
import com.pettrip.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) // dbms
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "user_id")
    private Long id;            //auto_increment사용해서 처음 등록한 사람은 1, 그다음 2... 이런식으로 저장

    //실명
    @Column(nullable = false, length = 20)
    private String realname;    //실명을 저장하는 entity

    @Column(nullable = false, length = 20)
    private String nickname;    //커뮤니티에서 활동하는 nickname을 추가

    //아이디이자 이메일
    @Column(nullable = false, length = 50, unique = true)
    private String username;    //로그인시 사용할 아이디이자 이메일

    @Column(nullable = false, length = 50)
    private String password;    //비밀번호, 저장시 암호화하여 저장

    @Column(length = 255)
    private String profileImageUrl;  //이미지는 다른곳에 저장후 링크를 가져오는 방식

    @Column(nullable = false, length = 255)
    private String address;     //사용자의 주소 저장

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<UserPloggingGroupApplyment> //userPloggingGroupApplyments;

    public static User toUser(JoinDTO joinDTO) {
        User user = new User();
        user.setRealname(joinDTO.getRealname());
        user.setNickname(joinDTO.getNickname());
        user.setUsername(joinDTO.getUsername());
        user.setPassword(joinDTO.getPassword());
        user.setAddress(joinDTO.getAddress());
        return user;
    }

    public static User toUser(LoginDTO loginDTO) {
        User user = new User();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(loginDTO.getPassword());
        return user;
    }
}