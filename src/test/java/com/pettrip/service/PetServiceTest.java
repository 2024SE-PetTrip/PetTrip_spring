package com.pettrip.service;

import com.pettrip.domain.User;
import com.pettrip.domain.care.Pet;
import com.pettrip.repository.PetRepository;
import com.pettrip.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PetServiceTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    void testSaveAndRetrievePet() {
        User user = userRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("User with ID 2 not found"));

        Pet pet = Pet.builder()
                .user(user)
                .petName("Dubu")
                .petAge(1)
                .breed("maltizu")
                .petImageUrl("http://example.com/maltizu.jpg")
                .build();

        // When
        Pet savedPet = petRepository.save(pet); // save()는 저장된 Pet 객체를 반환
        Optional<Pet> retrievedPetOptional = petRepository.findById(savedPet.getPetId()); // getPetId()를 사용해 ID를 전달

        // Then
        assertTrue(retrievedPetOptional.isPresent());
        Pet retrievedPet = retrievedPetOptional.get();
        assertEquals("Dubu", retrievedPet.getPetName());
    }
}
