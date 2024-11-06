package togathering.Plogging.domain;

import lombok.*;
import com.pettrip.app.dto.JoinDTO;
import com.pettrip.app.dto.LoginDTO;
import com.pettrip.domain.common.BaseEntity;

import javax.persistence.*;
import java.util.List;

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
    private Long id;

    //실명
    @Column(nullable = false, length = 20)
    private String realname;

    @Column(nullable = false, length = 20)
    private String nickname;

    //아이디이자 이메일
    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(length = 255)
    private String profile_image_url;

    @Column(nullable = false, length = 255)
    private String address;

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<UserPloggingGroupApplyment> //userPloggingGroupApplyments;

    public static User toUser(JoinDTO joinDTO) {
        User user = new User();
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