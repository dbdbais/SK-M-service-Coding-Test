package org.sk.task.user.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.sk.task.board.dto.BoardSpecDto;
import org.sk.task.board.entity.Board;
import org.sk.task.common.code.StatusCode;
import org.sk.task.session.service.SessionService;
import org.sk.task.user.dto.*;
import org.sk.task.user.entity.User;
import org.sk.task.user.repoisitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SessionService sessionService;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SessionService sessionService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sessionService = sessionService;
    }

    public StatusCode registerUser(UserRegisterDto userRegisterDto){

        if(userRepository.existsByNickname(userRegisterDto.getNickname())){
            return StatusCode.DUPLICATE_NICKNAME;
        }

        User newUser = User.builder()
                .nickname(userRegisterDto.getNickname())
                .password(bCryptPasswordEncoder.encode(userRegisterDto.getPassword()))
                .email(userRegisterDto.getEmail())
                .build();

        userRepository.save(newUser);

        return StatusCode.SUCCESS;
    }

    public UserLoginResponseDto loginUser(UserLoginDto userLoginDTO){
        Optional<User> sUser = userRepository.findByNickname(userLoginDTO.getNickname());

        if(sUser.isEmpty()){
            return new UserLoginResponseDto(StatusCode.NO_USER);
        }

        if(bCryptPasswordEncoder.matches(userLoginDTO.getPassword(),sUser.get().getPassword())){

            if(sessionService.isLogin(sUser.get().getUserId())){
                return new UserLoginResponseDto(StatusCode.ALREADY_LOGIN);
            }
            String sessionId = UUID.randomUUID().toString(); // 고유 세션 ID 생성
            sessionService.createSession(sUser.get().getUserId(),sessionId); // DB에 세션 저장
            ResponseCookie cookie = ResponseCookie.from("SESSION_ID", sessionId)
                    .httpOnly(true)
                    .path("/")
                    .build();

            return new UserLoginResponseDto(cookie,StatusCode.SUCCESS);
        }
        else{
            return new UserLoginResponseDto(StatusCode.WRONG_PW);
        }
    }

    public UserLogoutResponseDto logoutUser(String sessionIdFromCookie){
        Long userId = sessionService.getUserIdBySessionId(sessionIdFromCookie);

        Optional<User> sUser = userRepository.findById(userId);

        if(sUser.isEmpty()){
            return new UserLogoutResponseDto(StatusCode.NO_USER);
        }

        if (!sessionService.sessionCheck(userId, sessionIdFromCookie)) {
            return new UserLogoutResponseDto(StatusCode.FORBIDDEN_ACCESS); // 세션 검증 실패
        }


        if(sessionService.isLogin(userId)){
            sessionService.deleteSession(userId);
            ResponseCookie cookie = ResponseCookie.from("SESSION_ID", "")
                    .httpOnly(true)
                    .path("/")
                    .maxAge(0) // 쿠키 만료 시간 0으로 설정
                    .build();

            return new UserLogoutResponseDto(cookie,StatusCode.SUCCESS);
        }
        else{
            return new UserLogoutResponseDto(StatusCode.BAD_REQUEST);
        }
    }

    public List<BoardSpecDto> getWrittenBoard(String sessionIdFromCookie){
        Long userId = sessionService.getUserIdBySessionId(sessionIdFromCookie);
        Optional<User> sUser = userRepository.findById(userId);

        if(sUser.isEmpty()){
            return null;
        }

        List<Board> boards = sUser.get().getWrittenBoard();

        return boards.stream()
                .map(board -> BoardSpecDto.builder()
                        .title(board.getTitle())
                        .authorName(board.getAuthorName())
                        .content(board.getContent())
                        .view(board.getView())
                        .createdAt(board.getCreatedAt())
                        .build())
                .toList();
    }

}
