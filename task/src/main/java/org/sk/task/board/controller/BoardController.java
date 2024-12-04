package org.sk.task.board.controller;

import org.sk.task.board.dto.BoardDeleteDto;
import org.sk.task.board.dto.BoardModifyDto;
import org.sk.task.board.dto.BoardRegisterDto;
import org.sk.task.board.service.BoardService;
import org.sk.task.common.ResponseDto;
import org.sk.task.common.code.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> insert(@RequestPart BoardRegisterDto boardRegisterDto,
                                              @RequestPart(required = false) MultipartFile file) throws IOException {
        return ResponseDto.response(boardService.insertBoard(boardRegisterDto,file));
    }

    @GetMapping("/view/{no}")
    public ResponseEntity<ResponseDto> getBoard(@PathVariable Long no){
        return ResponseDto.response(StatusCode.SUCCESS,boardService.getSpecificBoard(no));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteBoard(@RequestBody BoardDeleteDto boardDeleteDto){
        return ResponseDto.response(StatusCode.SUCCESS,boardService.deleteBoard(boardDeleteDto));
    }

    @GetMapping("/asc")
    public ResponseEntity<ResponseDto> getAscBoard(@RequestParam int page){
        return ResponseDto.response(StatusCode.SUCCESS,boardService.getBoardAsc(page));
    }

    @GetMapping("/desc")
    public ResponseEntity<ResponseDto> getDescBoard(@RequestParam int page){
        return ResponseDto.response(StatusCode.SUCCESS,boardService.getBoardDesc(page));
    }

    @PutMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestPart BoardModifyDto boardModifyDto,
                                              @RequestPart(required = false) MultipartFile file) throws IOException {
        return ResponseDto.response(boardService.modifyBoard(boardModifyDto,file));
    }

    @GetMapping("/condition/author")
    public ResponseEntity<ResponseDto> getConditionAuthor(@RequestParam String authorName, @RequestParam int page){
        return ResponseDto.response(StatusCode.SUCCESS,boardService.getBoardAuthor(authorName, page));
    }

    @GetMapping("/condition/title")
    public ResponseEntity<ResponseDto> getConditionTitle(@RequestParam String title, @RequestParam int page){
        return ResponseDto.response(StatusCode.SUCCESS,boardService.getBoardTitle(title, page));
    }

}
