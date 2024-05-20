package com.ssafy.tripmate.board.controller;

import com.ssafy.tripmate.board.dto.*;
import com.ssafy.tripmate.board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @ExceptionHandler
    public ResponseEntity<String> handler(Exception e) {
        logger.error("board.error>>>>>>>>>>>>>>>>>>>>msg:{}", e.getMessage());

        //error 메세지가 한글인 경우 깨지므로 한글 처리를 위한 설정
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset-UTF-8");

        String msg = "";
        if (e instanceof BoardException) {
            msg = e.getMessage();
        } else {
            msg = "처리 중 오류 발생";
        }
        return new ResponseEntity<>(msg, resHeader, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("")
    public ResponseEntity<?> loadBoard() {
        List<BoardResponseDto> boards = service.findAll();
        if (boards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> loadBoardByCategory(@PathVariable("category") String category) {
        List<BoardResponseDto> boards = service.findAllByCategory(category);
        if (boards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> loadBoardByUserId(@PathVariable("category") String category, @RequestParam("userId") int userId) {
        // TODO: userID -> ID로 해야 함
        List<BoardResponseDto> boards = service.findAllByUserId(category, userId);
        if (boards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> loadBoardByKeyword(@PathVariable("category") String category, @RequestParam("keyword") String keyword) {
        List<BoardResponseDto> boards = service.findAllByKeyword(category, keyword);
        if (boards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> loadBoard(@PathVariable("boardId") int boardId) {
        Board board = service.findByBoardId(boardId);
        if (board == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Integer> deleteBoard(@PathVariable("boardId") int boardId) {
        return new ResponseEntity<>(service.delete(boardId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> createBoard(@RequestBody BoardSaveDto boardSaveDto) {
        return new ResponseEntity<>(service.insert(boardSaveDto), HttpStatus.OK);
    }

    @PutMapping("/{boardId")
    public ResponseEntity<Integer> updateBoard(@PathVariable("boardId") int boardId, @RequestBody BoardUpdateDto boardUpdateDto) {
        return new ResponseEntity<>(service.update(boardId, boardUpdateDto), HttpStatus.OK);
    }
}
