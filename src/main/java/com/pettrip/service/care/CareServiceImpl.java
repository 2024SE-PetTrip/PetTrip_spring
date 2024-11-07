package com.pettrip.service.care;

import com.pettrip.apiPayload.code.status.ErrorStatus;
import com.pettrip.apiPayload.exception.handler.AppHandler;
import com.pettrip.app.dto.care.CareRequestDTO;
import com.pettrip.app.dto.care.CareResponseDTO;
import com.pettrip.app.dto.care.EvaluationRequestDTO;
import com.pettrip.app.dto.care.EvaluationResponseDTO;
import com.pettrip.converter.CareConverter;
import com.pettrip.domain.User;
import com.pettrip.domain.care.CareRequest;
import com.pettrip.domain.care.Evaluation;
import com.pettrip.domain.enums.CareRequestStatus;
import com.pettrip.repository.CareRequestRepository;
import com.pettrip.repository.EvaluationRepository;
import com.pettrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CareServiceImpl implements CareService {

    private final CareRequestRepository careRequestRepository;

    private final UserRepository userRepository;

    private final EvaluationRepository evaluationRepository;

    @Override
    @Transactional
    public CareResponseDTO createCareRequest(CareRequestDTO careRequestDTO) {
        User requester = userRepository.findById(careRequestDTO.getRequesterId())
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));

        CareRequest careRequest = CareConverter.toCareRequest(careRequestDTO);
        careRequest.setRequester(requester);

        careRequestRepository.save(careRequest);

        return CareConverter.toCareResponseDTO(careRequest);
    }

    @Override
    public List<CareResponseDTO> getAllCareRequest() {
        List<CareRequest> careRequests = careRequestRepository.findAll();

        return CareConverter.toCareResponseDTOList(careRequests);
    }

    @Override
    public CareResponseDTO getCareRequestById(Long requestId) {
        CareRequest careRequest = careRequestRepository.findById(requestId)
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_CARE_REQUEST));

        return CareConverter.toCareResponseDTO(careRequest);
    }

    @Override
    public List<CareResponseDTO> getCareRequestsByRequesterId(Long requesterId) {
        List<CareRequest> careRequests = careRequestRepository.findByRequesterId(requesterId);

        return CareConverter.toCareResponseDTOList(careRequests);
    }

    @Override
    public List<CareResponseDTO> getCareRequestsByStatus(CareRequestStatus status) {
        List<CareRequest> careRequests = careRequestRepository.findByStatus(status);

        return CareConverter.toCareResponseDTOList(careRequests);
    }

    @Override
    @Transactional
    public CareResponseDTO updateCareRequest(Long requestId, CareRequestDTO careRequestDto) {
        User requester = userRepository.findById(careRequestDto.getRequesterId())
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));

        CareRequest careRequest = careRequestRepository.findById(requestId)
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_CARE_REQUEST));

        careRequest.setRequester(requester);
        careRequest.setStartDate(careRequestDto.getStartDate());
        careRequest.setEndDate(careRequestDto.getEndDate());
        careRequest.setRequestDescription(careRequestDto.getRequestDescription());
        careRequest.setRequestImageUrl(careRequestDto.getRequestImageUrl());

        careRequestRepository.save(careRequest);

        return CareConverter.toCareResponseDTO(careRequest);
    }

    @Override
    @Transactional
    public void deleteCareRequest(Long requestId) {
        CareRequest careRequest = careRequestRepository.findById(requestId)
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_CARE_REQUEST));

        careRequestRepository.delete(careRequest);
    }


    @Override
    @Transactional
    public CareResponseDTO matchCareProvider(Long requestId, Long providerId) {
        CareRequest careRequest = careRequestRepository.findById(requestId)
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_CARE_REQUEST));

        User provider = userRepository.findById(providerId)
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));

        if (!CareRequestStatus.PENDING.equals(careRequest.getStatus())) {
            throw new AppHandler(ErrorStatus.INVALID_MATCH_STATUS);
        }

        careRequest.setProvider(provider);
        careRequest.setStatus(CareRequestStatus.MATCHED);

        careRequestRepository.save(careRequest);

        return CareConverter.toCareResponseDTO(careRequest);
    }

    @Override
    @Transactional
    public EvaluationResponseDTO addEvaluation(EvaluationRequestDTO requestDto) {
        CareRequest careRequest = careRequestRepository.findById(requestDto.getCareRequestId())
                .orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_CARE_REQUEST));

        if (!CareRequestStatus.COMPLETED.equals(careRequest.getStatus())) {
            throw new AppHandler(ErrorStatus.INVALID_EVALUATION_STATUS);
        }

        Evaluation evaluation = new Evaluation();
        evaluation.setCareRequest(careRequest);
        evaluation.setRating(requestDto.getRating());
        evaluation.setFeedback(requestDto.getFeedback());

        careRequest.setStatus(CareRequestStatus.EVALUATED);

        evaluationRepository.save(evaluation);

        return CareConverter.toEvaluationResponseDTO(evaluation);
    }


    // 특정 돌봄 요청자와 돌봄 제공자의 평가 내역을 모두 가져온다.
//    public List<EvaluationResponseDTO> getEvaluationsByUserIds(Long requesterId, Long providerId) {
//        List<Evaluation> evaluations = evaluationRepository.findByRequesterIdAndProviderId(requesterId, providerId);
//        if (evaluations == null) {
//            return null;
//        }
//        return CareConverter.toEvaluationResponseDTOList(evaluations);
//    }

    // 돌봄 제공자의 평가 내역을 모두 가져온다.
    public List<EvaluationResponseDTO> getEvaluationsByUserId(Long providerId) {
        List<Evaluation> evaluations = evaluationRepository.findByProviderId(providerId);

        return CareConverter.toEvaluationResponseDTOList(evaluations);
    }

}
