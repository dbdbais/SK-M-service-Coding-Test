package org.sk.task.board.service;

import jakarta.transaction.Transactional;
import org.sk.task.board.dto.*;
import org.sk.task.board.entity.Board;
import org.sk.task.board.repository.BoardRepository;
import org.sk.task.common.code.StatusCode;
import org.sk.task.user.entity.User;
import org.sk.task.user.repoisitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Transactional
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public StatusCode insertBoard(BoardRegisterDto boardRegisterDto) {
        Optional<User> sUser = userRepository.findById(boardRegisterDto.getUserId());

        if(sUser.isEmpty()){
            return StatusCode.BAD_REQUEST;
        }

        Board newBoard = Board.builder()
                .title(boardRegisterDto.getTitle())
                .authorId(sUser.get().getUserId())
                .authorName(sUser.get().getNickname())
                .content(boardRegisterDto.getContent())
                .filePath(boardRegisterDto.getFilepath())
                .build();

        boardRepository.save(newBoard);
        sUser.get().getWrittenBoard().add(newBoard);

        return StatusCode.SUCCESS;
    }

    public BoardSpecDto getSpecificBoard(Long no){
        Board tBoard = boardRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("Board not found with id: " + no));

        // view 증가
        tBoard.increase();

        return BoardSpecDto.builder()
                .title(tBoard.getTitle())
                .authorName(tBoard.getAuthorName())
                .content(tBoard.getContent())
                .view(tBoard.getView())
                .createdAt(tBoard.getCreatedAt())
                .build();
    }

    //public List<BoardListDto> getConditionSearch()

//    public StatusCode modifyBoard(BoardModifyDto boardModifyDto){
//        Optional<User> sUser = userRepository.findById(boardModifyDto.getUserId());
//        Optional<Board> sBoard = boardRepository.findById(boardModifyDto.getBoardId());
//
//        if(sUser.isEmpty() || sBoard.isEmpty()){
//            return StatusCode.BAD_REQUEST;
//        }
//
//    }

    public StatusCode deleteBoard(BoardDeleteDto boardDeleteDto){
        Optional<User> sUser = userRepository.findById(boardDeleteDto.getUserId());
        Optional<Board> sBoard = boardRepository.findById(boardDeleteDto.getBoardId());

        if(sUser.isEmpty() || sBoard.isEmpty()){
            return StatusCode.BAD_REQUEST;
        }

        if(Objects.equals(sUser.get().getUserId(), sBoard.get().getAuthorId())){
            boardRepository.delete(sBoard.get());
            //지워버린다.
            return StatusCode.SUCCESS;
        }
        else{
            return StatusCode.FORBIDDEN_ACCESS;
        }

    }

    public List<BoardListDto> getBoardDesc(int page){
        Pageable pageable = PageRequest.of(page-1,10);
        //10개의 리스트의 페이지로 쪼개서 리턴한다.

        Page<Board> pages = boardRepository.findAllByOrderByCreatedAtDesc(pageable);

        return pages.getContent().stream()
                .map(board -> BoardListDto.builder()
                        .title(board.getTitle())
                        .authorName(board.getAuthorName())
                        .view(board.getView())
                        .isFile(board.getFilePath() != null)
                        .registerDate(board.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public List<BoardListDto> getBoardAsc(int page){
        Pageable pageable = PageRequest.of(page-1,10);
        //10개의 리스트의 페이지로 쪼개서 리턴한다.

        Page<Board> pages = boardRepository.findAllByOrderByCreatedAtAsc(pageable);

        return pages.getContent().stream()
                .map(board -> BoardListDto.builder()
                        .title(board.getTitle())
                        .authorName(board.getAuthorName())
                        .view(board.getView())
                        .isFile(board.getFilePath() != null)
                        .registerDate(board.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }


}
