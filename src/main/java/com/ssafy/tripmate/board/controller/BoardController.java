package com.ssafy.tripmate.board.controller;

import com.ssafy.tripmate.board.dto.*;
import com.ssafy.tripmate.board.service.BoardService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@Slf4j
@Tag(name = "Board 컨트롤러", description = "게시글 CRUD 관련 클래스")
public class BoardController {

    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {


        @ExceptionHandler(BoardException.class)
        public ResponseEntity<String> handleException(BoardException e) {
            log.error("board.error >>> msg: {}", e.getMessage());

            HttpHeaders resHeader = new HttpHeaders();
            resHeader.add("Content-Type", "application/json;charset=UTF-8");
            return new ResponseEntity<>("Board 처리 중 오류 발생:" + e.getMessage() , resHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공"),
            @ApiResponse(responseCode = "204", description = "게시물 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> loadBoard() {
        List<BoardResponseDto> boards = service.findAll();
        if (boards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/search/{category}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "검색어별 게시글 목록 조회 성공"),
            @ApiResponse(responseCode = "204", description = "게시물 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> loadBoard(
            @PathVariable("category") String category,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String keyword) {

        List<BoardResponseDto> boards;
        if (userId != null) {
            // 사용자 ID에 따른 게시글 조회
            boards = service.findAllByUserId(category, userId);
        } else if (keyword != null) {
            // 키워드에 따른 게시글 조회
            boards = service.findAllByKeyword(category, keyword);
        } else {
            // 카테고리에 따른 게시글 조회
            boards = service.findAllByCategory(category);
        }

        if (boards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시물 조회 성공"),
            @ApiResponse(responseCode = "204", description = "게시물 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> loadBoard(@PathVariable("boardId") int boardId) {
        Board board = service.findByBoardId(boardId);
        if (board == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시판 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "게시물 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> deleteBoard(@PathVariable("boardId") int boardId) {
        service.delete(boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시판 작성 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> createBoard(@RequestBody BoardSaveDto boardSaveDto) {
        log.debug("[BOARD]insert>>>>>>>>>>>", boardSaveDto);
        return new ResponseEntity<>(service.insert(boardSaveDto), HttpStatus.OK);
    }

    @PutMapping("/{boardId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시판 수정 성공"),
            @ApiResponse(responseCode = "400", description = "게시물 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> updateBoard(@PathVariable("boardId") int boardId, @RequestBody BoardUpdateDto boardUpdateDto) {
        return new ResponseEntity<>(service.update(boardId, boardUpdateDto), HttpStatus.OK);
    }
}
