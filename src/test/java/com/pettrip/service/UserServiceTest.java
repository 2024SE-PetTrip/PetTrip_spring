package com.pettrip.service;

import com.pettrip.app.dto.JoinDTO;
import com.pettrip.domain.User;
import com.pettrip.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    User createUser() {
        JoinDTO joinDTO = new JoinDTO();
        joinDTO.setRealname("Tester3");
        joinDTO.setNickname("tst3");
        joinDTO.setUsername("Tester3@google.com");
        joinDTO.setPassword("p333");
        joinDTO.setUserAddress("seoul");

        User user = User.toUser(joinDTO);

        return userRepository.save(user);
    }

    @Test
    void createTestUser() {
        User user = createUser();

        assertThat(user.getId()).isNotNull();
        assertThat(user.getCreatedAt()).isNotNull();
        assertThat(user.getUpdatedAt()).isNotNull();
        assertThat(user.getRealname()).isEqualTo("Tester3");
        assertThat(user.getNickname()).isEqualTo("tst3");
        assertThat(user.getUsername()).isEqualTo("Tester3@google.com");
        assertThat(user.getPassword()).isEqualTo("p333");
        assertThat(user.getAddress()).isEqualTo("seoul");
    }
}