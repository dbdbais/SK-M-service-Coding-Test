package org.sk.task.user.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.sk.task.board.dto.BoardSpecDto;
import org.sk.task.board.entity.Board;
import org.sk.task.common.code.StatusCode;
import org.sk.task.user.dto.UserLoginDto;
import org.sk.task.user.dto.UserRegisterDto;
import org.sk.task.user.entity.User;
import org.sk.task.user.repoisitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    public StatusCode loginUser(UserLoginDto userLoginDTO){
        Optional<User> sUser = userRepository.findByNickname(userLoginDTO.getNickname());

        if(sUser.isEmpty()){
            return StatusCode.NO_USER;
        }

        if(bCryptPasswordEncoder.matches(userLoginDTO.getPassword(),sUser.get().getPassword())){
            return StatusCode.SUCCESS;
        }
        else{
            return StatusCode.WRONG_PW;
        }
    }

    public List<BoardSpecDto> getWrittenBoard(Long userId){
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
