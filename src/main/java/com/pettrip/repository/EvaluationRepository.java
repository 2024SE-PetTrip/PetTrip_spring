package com.pettrip.repository;

import com.pettrip.domain.care.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    @Query("SELECT e FROM Evaluation e WHERE e.careRequest.provider.id = :providerId")
    List<Evaluation> findByProviderId(Long providerId);
}
