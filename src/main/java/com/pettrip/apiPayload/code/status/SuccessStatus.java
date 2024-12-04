package com.pettrip.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.pettrip.apiPayload.code.BaseCode;
import com.pettrip.apiPayload.code.ReasonDTO;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    _OK(HttpStatus.OK, 200, "성공입니다."),

    // 모임 관련

    WALK_GROUP_REQUEST_OK(HttpStatus.OK, 200, "산책 모임 요청 생성 성공"),
    WALK_GROUP_LIST_OK(HttpStatus.OK, 200, "산책 모임 리스트 조회 성공"),
    WALK_GROUP_DETAIL_OK(HttpStatus.OK, 200, "산책 모임 상세 조회 성공"),
    WALK_GROUP_CREATOR_DETAIL_OK(HttpStatus.OK, 200, "산책 모임 작성자 시점 상세 조회 성공"),
    WALK_GROUP_JOIN_REQUEST_OK(HttpStatus.OK, 200, "산책 모임 참가 신청 성공"),
    WALK_GROUP_APPLICANT_ACCEPTED(HttpStatus.OK, 200, "산책 모임 참가 신청 수락 성공"),
    WALK_GROUP_APPLICANT_REJECTED(HttpStatus.OK, 200, "산책 모임 참가 신청 거절 성공"),
    WALK_GROUP_MEMBER_REMOVED(HttpStatus.OK, 200, "산책 모임 멤버 삭제 성공"),
    //돌봄
    CARE_REQUEST_OK(HttpStatus.OK, 200, "돌봄 요청 생성 성공"),
    CARE_REQUEST_LIST_OK(HttpStatus.OK, 200, "돌봄 요청 리스트 조회 성공"),
    CARE_REQUEST_DETAIL_OK(HttpStatus.OK, 200, "돌봄 요청 상세 조회 성공"),
    CARE_REQUEST_UPDATED(HttpStatus.OK, 200, "돌봄 요청 업데이트 성공"),
    CARE_REQUEST_DELETED(HttpStatus.OK, 200, "돌봄 요청 삭제 성공"),
    CARE_PROVIDER_MATCHED(HttpStatus.OK, 200, "돌봄 매칭 성공"),
    CARE_REQUEST_COMPLETED(HttpStatus.OK, 200, "돌봄 완료 변경 성공"),
    CARE_EVALUATION_OK(HttpStatus.OK, 200, "돌봄 평가 완료"),
    CARE_EVALUATION_LIST_OK(HttpStatus.OK, 200, "돌봄 제공자의 평가 내역 조회 성공"),

    // 회원가입 관련
    REGISTER_JOIN_OK(HttpStatus.OK, 200, "회원가입 성공!"),

    // 코스에디터 관련
    COURSE_CREATED_OK(HttpStatus.OK, 200, "코스 생성 성공!"),
    COURSE_UPDATED_OK(HttpStatus.OK, 200, "코스 수정 성공!"),
    COURSE_DELETED_OK(HttpStatus.OK, 200, "코스 삭제 성공!"),
    COURSE_LIST_OK(HttpStatus.OK, 200, "코스 불러오기 성공!"),
    LIKE_SUCCESS(HttpStatus.OK, 200, "좋아요 성공!"),
    DISLIKE_SUCCESS(HttpStatus.OK, 200, "좋아요 해제 성공!"),
    COMMENT_ADDED_OK(HttpStatus.OK, 200, "댓글 작성 성공!"),
    COMMENT_UPDATED_OK(HttpStatus.OK, 200, "댓글 수정 성공!"),

    COMMENT_DELETED_OK(HttpStatus.OK, 200, "댓글 삭제 성공!"),

    //사용자프로필 - 반려동물확인 외부 api관련
    VALIDATION_SUCCESS(HttpStatus.OK, 200, "반려동물 인증 성공!"),
    PET_ADDED_OK(HttpStatus.OK, 200, "반려동물 추가 성공!");
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
