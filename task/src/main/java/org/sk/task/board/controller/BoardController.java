package org.sk.task.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "게시글 등록", description = "게시글을 등록하는 API입니다. 파일 업로드도 가능합니다.")
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> insert(
            @Parameter(description = "세션 ID", required = true) @CookieValue("SESSION_ID") String sessionId,
            @Parameter(description = "게시글 등록 정보") @RequestPart BoardRegisterDto boardRegisterDto,
            @Parameter(description = "첨부 파일", required = false) @RequestPart(required = false) MultipartFile file) throws IOException {
        return ResponseDto.response(boardService.insertBoard(sessionId, boardRegisterDto, file));
    }

    @Operation(summary = "게시글 조회", description = "특정 게시글을 조회하는 API입니다.")
    @GetMapping("/view/{no}")
    public ResponseEntity<ResponseDto> getBoard(
            @Parameter(description = "조회할 게시글 ID") @PathVariable Long no) {
        return ResponseDto.response(StatusCode.SUCCESS, boardService.getSpecificBoard(no));
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제하는 API입니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteBoard(
            @Parameter(description = "세션 ID", required = true) @CookieValue("SESSION_ID") String sessionId,
            @Parameter(description = "삭제할 게시글 ID") @RequestParam Long boardId) {
        return ResponseDto.response(StatusCode.SUCCESS, boardService.deleteBoard(sessionId, boardId));
    }

    @Operation(summary = "오름차순 게시글 조회", description = "페이지네이션을 이용해 오름차순으로 게시글을 조회하는 API입니다.")
    @GetMapping("/asc")
    public ResponseEntity<ResponseDto> getAscBoard(
            @Parameter(description = "페이지 번호") @RequestParam int page) {
        return ResponseDto.response(StatusCode.SUCCESS, boardService.getBoardAsc(page));
    }

    @Operation(summary = "내림차순 게시글 조회", description = "페이지네이션을 이용해 내림차순으로 게시글을 조회하는 API입니다.")
    @GetMapping("/desc")
    public ResponseEntity<ResponseDto> getDescBoard(
            @Parameter(description = "페이지 번호") @RequestParam int page) {
        return ResponseDto.response(StatusCode.SUCCESS, boardService.getBoardDesc(page));
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정하는 API입니다. 파일도 함께 수정할 수 있습니다.")
    @PutMapping("/modify")
    public ResponseEntity<ResponseDto> modify(
            @Parameter(description = "세션 ID", required = true) @CookieValue("SESSION_ID") String sessionId,
            @Parameter(description = "수정할 게시글 정보") @RequestPart BoardModifyDto boardModifyDto,
            @Parameter(description = "첨부 파일", required = false) @RequestPart(required = false) MultipartFile file) throws IOException {
        return ResponseDto.response(boardService.modifyBoard(sessionId, boardModifyDto, file));
    }

    @Operation(summary = "작성자 조건으로 게시글 조회", description = "작성자명을 기준으로 게시글을 조회하는 API입니다.")
    @GetMapping("/condition/author")
    public ResponseEntity<ResponseDto> getConditionAuthor(
            @Parameter(description = "작성자 이름") @RequestParam String authorName,
            @Parameter(description = "페이지 번호") @RequestParam int page) {
        return ResponseDto.response(StatusCode.SUCCESS, boardService.getBoardAuthor(authorName, page));
    }

    @Operation(summary = "제목 조건으로 게시글 조회", description = "제목을 기준으로 게시글을 조회하는 API입니다.")
    @GetMapping("/condition/title")
    public ResponseEntity<ResponseDto> getConditionTitle(
            @Parameter(description = "게시글 제목") @RequestParam String title,
            @Parameter(description = "페이지 번호") @RequestParam int page) {
        return ResponseDto.response(StatusCode.SUCCESS, boardService.getBoardTitle(title, page));
    }
}
