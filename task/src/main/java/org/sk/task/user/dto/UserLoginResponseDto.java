package org.sk.task.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sk.task.common.code.StatusCode;
import org.springframework.http.ResponseCookie;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDto {

    @Schema(description = "로그인 후 반환되는 세션 쿠키", example = "SESSION_ID=abcd1234; Path=/; HttpOnly")
    private ResponseCookie cookie;

    @Schema(description = "로그인 처리 결과 상태 코드", example = "SUCCESS")
    private StatusCode statusCode;

    public UserLoginResponseDto(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
