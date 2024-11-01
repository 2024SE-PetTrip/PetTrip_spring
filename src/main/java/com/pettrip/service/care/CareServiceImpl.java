package com.pettrip.service.care;

import com.pettrip.apiPayload.code.status.ErrorStatus;
import com.pettrip.apiPayload.exception.handler.AppHandler;
import com.pettrip.app.dto.CareRequestDTO;
import com.pettrip.app.dto.CareResponseDTO;
import com.pettrip.converter.CareRequestConverter;
import com.pettrip.domain.User;
import com.pettrip.domain.care.CareRequest;
import com.pettrip.domain.enums.CareRequestStatus;
import com.pettrip.repository.CareRequestRepository;
import com.pettrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CareServiceImpl implements CareService {

    private final CareRequestRepository careRequestRepository;

    private final UserRepository userRepository;

    @Override
    public CareResponseDTO createCareRequest(CareRequestDTO careRequestDto) {
        User user = userRepository.findById(careRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CareRequest careRequest = CareRequestConverter.toCareRequest(careRequestDto);
        careRequest.setUser(user);

        careRequestRepository.save(careRequest);

        return CareRequestConverter.toCareResponseDTO(careRequest);
    }

    @Override
    public List<CareResponseDTO> getAllCareRequest() {
        List<CareRequest> careRequests = careRequestRepository.findAll();

        return CareRequestConverter.toCareResponseDTOList(careRequests);
    }

    @Override
    public CareRequest getCareRequestById(Long requestId) {
        return careRequestRepository.findById(requestId).orElse(null);
    }

    @Override
    public List<CareRequest> getCareRequestsByUserId(Long userId) {
        return careRequestRepository.findByUserId(userId);
    }

    @Override
    public List<CareRequest> getCareRequestsByStatus(CareRequestStatus status) {
        return careRequestRepository.findByStatus(status);
    }

    @Override
    public CareRequest updateCareRequest(Long requestId, CareRequestDTO careRequestDto) {
        return null;
    }

    @Override
    public void deleteCareRequest(Long requestId) {

    }

    @Override
    public CareRequest matchCareProvider(Long requestId, Long providerId) {
        return null;
    }

    @Override
    public CareRequest completeCareRequest(Long requestId) {
        return null;
    }
}
