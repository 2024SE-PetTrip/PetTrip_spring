package com.pettrip.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.pettrip.apiPayload.code.BaseErrorCode;
import com.pettrip.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 기본 응답(http)
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,400,"잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,401,"인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, 403, "금지된 요청입니다."),


    // 회원가입 실패
    REGISTER_ALREADY_USER_EXIST(HttpStatus.BAD_REQUEST, 41001, "이미 있는 아이디(이메일)입니다."),
    REGISTER_NICKNAME_EMPTY(HttpStatus.BAD_REQUEST, 41002, "닉네임을 입력해주세요."),
    REGISTER_NICKNAME_PUNCT(HttpStatus.BAD_REQUEST, 41003, "닉네임에는 특수문자가 들어갈 수 없습니다."),
    REGISTER_NICKNAME_TOO_LONG(HttpStatus.BAD_REQUEST, 41004, "닉네임은 최대 50자입니다."),
    REGISTER_EMAIL_INVALID(HttpStatus.BAD_REQUEST, 41005, "이메일 형식이 아닙니다."),
    REGISTER_EMAIL_EMPTY(HttpStatus.BAD_REQUEST, 41006, "이메일을 입력해주세요."),
    REGISTER_EMAIL_TOO_LONG(HttpStatus.BAD_REQUEST, 41006, "이메일은 최대 50자입니다."),
    REGISTER_PASSWORD_INVALID(HttpStatus.BAD_REQUEST, 41007, "비밀번호 형식이 아닙니다."),
    REGISTER_PASSWORD_EMPTY(HttpStatus.BAD_REQUEST, 41008, "비밀번호를 입력해주세요."),
    REGISTER_PASSWORD_TOO_LONG(HttpStatus.BAD_REQUEST, 41009, "비밀번호는 최대 70자입니다."),

    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, 40401, "존재하지 않는 회원입니다."),
    NOT_FOUND_CARE_REQUEST(HttpStatus.BAD_REQUEST, 40404, "존재하지 않는 돌봄요청입니다."),
    INVALID_MATCH_STATUS(HttpStatus.BAD_REQUEST, 40405, "돌봄 서비스를 매칭할 수 없습니다."),
    INVALID_EVALUATION_STATUS(HttpStatus.BAD_REQUEST, 40406, "돌봄 서비스를 평가할 수 없습니다."),
    NOT_FOUND_CHATROOM(HttpStatus.BAD_REQUEST, 40407, "채팅방을 찾을 수 없습니다."),
    INVALID_STATUS_UPDATE(HttpStatus.BAD_REQUEST, 40408, "돌봄 게시글을 수정할 수 없는 상태입니다.");



    // 여기 관련해서 하고싶은 응답들 정의해주세요..

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
