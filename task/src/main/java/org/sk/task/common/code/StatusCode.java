package org.sk.task.common.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
    SUCCESS(HttpStatus.OK, null),
    WRONG_PW(HttpStatus.UNAUTHORIZED, "비밀번호가 잘못되었습니다. 다시 시도해 주세요."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    UNAUTHORIZED_REQUEST(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "권한이 없는 사용자입니다."),
    NO_USER(HttpStatus.BAD_REQUEST,"없는 사용자입니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST,"중복된 닉네임이 존재합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}