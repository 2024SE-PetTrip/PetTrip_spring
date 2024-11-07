package com.pettrip.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.pettrip.app.dto.JoinDTO;
import com.pettrip.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findById(Long id);

    boolean existsByUsername(String username);

    User findByUsername(String username);
}