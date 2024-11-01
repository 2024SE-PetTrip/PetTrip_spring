package com.pettrip.service;

import com.pettrip.domain.User;
import com.pettrip.app.dto.JoinDTO;
import com.pettrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

}

