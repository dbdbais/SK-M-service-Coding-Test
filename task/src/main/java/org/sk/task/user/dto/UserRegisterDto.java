package org.sk.task.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @Schema(description = "사용자의 닉네임", example = "홍길동")
    private String nickname;

    @Schema(description = "사용자의 이메일", example = "hong@example.com")
    private String email;

    @Schema(description = "사용자의 비밀번호", example = "password123")
    private String password;
}
