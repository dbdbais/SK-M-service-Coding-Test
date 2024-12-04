package org.sk.task.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.sk.task.common.ResponseDto;
import org.sk.task.common.code.StatusCode;
import org.sk.task.user.dto.*;
import org.sk.task.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody UserRegisterDto userRegisterDto){
        return ResponseDto.response(userService.registerUser(userRegisterDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody UserLoginDto userLoginDto){
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

    @DeleteMapping("/logout")
    public ResponseEntity<ResponseDto> logout(@CookieValue("SESSION_ID") String sessionId){
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

    @GetMapping("/list/{userId}")
    public ResponseEntity<ResponseDto> getWritten(@CookieValue("SESSION_ID") String sessionId){
        return ResponseDto.response(StatusCode.SUCCESS,userService.getWrittenBoard(sessionId));
    }







}
