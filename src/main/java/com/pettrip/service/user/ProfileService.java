package com.pettrip.service.user;

import com.pettrip.app.dto.user.ProfileDTO;
import com.pettrip.domain.User;
import com.pettrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileDTO getProfileById(Long id) {
        // UserRepository에서 User 정보를 조회
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            // 유저가 없을 경우 null 반환
            return null;
        }

        // User 정보를 ProfileDTO로 변환
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setNickname(user.getNickname());
        profileDTO.setAddress(user.getAddress());
        profileDTO.setProfileImageUrl(user.getProfileImageUrl());

        return profileDTO;
    }
}
