package org.sk.task.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.sk.task.common.ResponseDto;
import org.sk.task.common.code.StatusCode;
import org.sk.task.user.dto.*;
import org.sk.task.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "회원 등록", description = "새로운 사용자를 등록하는 API입니다.")
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(
            @Parameter(description = "사용자 등록 정보") @RequestBody UserRegisterDto userRegisterDto) {
        return ResponseDto.response(userService.registerUser(userRegisterDto));
    }

    @Operation(summary = "로그인", description = "사용자가 로그인하는 API입니다. 로그인 성공 시 세션 쿠키가 발급됩니다.")
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(
            @Parameter(description = "사용자 로그인 정보") @RequestBody UserLoginDto userLoginDto) {
        UserLoginResponseDto userLoginResponseDto = userService.loginUser(userLoginDto);
        if (userLoginResponseDto.getCookie() != null) {
            return ResponseEntity
                    .status(userLoginResponseDto.getStatusCode().getHttpStatus())
                    .header("Set-Cookie", userLoginResponseDto.getCookie().toString()) // 쿠키를 응답 헤더에 추가
                    .body(new ResponseDto(userLoginResponseDto.getStatusCode())); // ResponseDto 객체를 본문으로 설정
        } else {
            return ResponseEntity
                    .status(userLoginResponseDto.getStatusCode().getHttpStatus())
                    .body(new ResponseDto(userLoginResponseDto.getStatusCode())); // 상태 코드만 응답 본문에 포함
        }
    }

    @Operation(summary = "로그아웃", description = "사용자가 로그아웃하는 API입니다. 로그아웃 시 세션 쿠키가 삭제됩니다.")
    @DeleteMapping("/logout")
    public ResponseEntity<ResponseDto> logout(
            @Parameter(description = "세션 ID") @CookieValue("SESSION_ID") String sessionId) {
        UserLogoutResponseDto userLogoutResponseDto = userService.logoutUser(sessionId);
        if (userLogoutResponseDto.getCookie() != null) {
            return ResponseEntity
                    .status(userLogoutResponseDto.getStatusCode().getHttpStatus())
                    .header("Set-Cookie", userLogoutResponseDto.getCookie().toString()) // 쿠키를 응답 헤더에 추가
                    .body(new ResponseDto(userLogoutResponseDto.getStatusCode())); // ResponseDto 객체를 본문으로 설정
        } else {
            return ResponseEntity
                    .status(userLogoutResponseDto.getStatusCode().getHttpStatus())
                    .body(new ResponseDto(userLogoutResponseDto.getStatusCode())); // 상태 코드만 응답 본문에 포함
        }
    }

    @Operation(summary = "작성한 게시글 조회", description = "특정 사용자가 작성한 게시글을 조회하는 API입니다.")
    @GetMapping("/list")
    public ResponseEntity<ResponseDto> getWritten(
            @Parameter(description = "사용자 세션 ID") @CookieValue("SESSION_ID") String sessionId) {
        return ResponseDto.response(StatusCode.SUCCESS, userService.getWrittenBoard(sessionId));
    }
}
