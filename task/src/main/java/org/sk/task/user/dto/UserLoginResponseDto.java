package org.sk.task.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sk.task.common.code.StatusCode;
import org.springframework.http.ResponseCookie;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDto {
    private ResponseCookie cookie;
    private StatusCode statusCode;

    public UserLoginResponseDto(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
