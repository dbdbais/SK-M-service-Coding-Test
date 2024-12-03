package org.sk.task.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.sk.task.common.ResponseDto;
import org.sk.task.common.code.StatusCode;
import org.sk.task.user.dto.UserLoginDto;
import org.sk.task.user.dto.UserRegisterDto;
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
        return ResponseDto.response(userService.loginUser(userLoginDto));
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<ResponseDto> getWritten(@PathVariable Long userId){
        return ResponseDto.response(StatusCode.SUCCESS,userService.getWrittenBoard(userId));
    }







}
