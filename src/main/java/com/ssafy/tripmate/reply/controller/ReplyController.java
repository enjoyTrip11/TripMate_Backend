package com.ssafy.tripmate.reply.controller;

import com.ssafy.tripmate.board.dto.BoardException;
import com.ssafy.tripmate.reply.dto.ReplyException;
import com.ssafy.tripmate.reply.dto.ReplyResponseDto;
import com.ssafy.tripmate.reply.dto.ReplySaveDto;
import com.ssafy.tripmate.reply.dto.ReplyUpdateDto;
import com.ssafy.tripmate.reply.service.ReplyService;
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
@RequestMapping("/reply")
@Tag(name = "Reply 컨트롤러", description = "댓글 CRUD 관련 클래스")
@Slf4j
public class ReplyController {

    private final ReplyService service;

    public ReplyController(ReplyService service) {
        this.service = service;
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(ReplyException.class)
        public ResponseEntity<String> handleException(ReplyException e) {
            log.error("reply.error >>> msg: {}", e.getMessage());

            HttpHeaders resHeader = new HttpHeaders();
            resHeader.add("Content-Type", "application/json;charset=UTF-8");

            return new ResponseEntity<>("reply 처리 중 오류 발생:" + e.getMessage(), resHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "전체 댓글 조회 성공"),
            @ApiResponse(responseCode = "204", description = "댓글 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> loadReply(@RequestParam("boardId") int boardId) {
        List<ReplyResponseDto> replies = service.searchAll(boardId);
        if (replies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

    @GetMapping("/{replyId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "아이디별 댓글 조회 성공"),
            @ApiResponse(responseCode = "204", description = "댓글 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> findReplyById(@RequestParam("boardId") int boardId, @PathVariable("replyId") int replyId) {
        ReplyResponseDto reply = service.search(boardId, replyId);
        if (reply == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "댓글 생성 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> createReply(@RequestBody ReplySaveDto replySaveDto) {
        return new ResponseEntity<>(service.create(replySaveDto), HttpStatus.OK);
    }

    @PutMapping("/{replyId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "댓글 수정 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> updateReply(@RequestParam("boardId") int boardId, @PathVariable("replyId") int replyId, @RequestBody ReplyUpdateDto replyUpdateDto) {
        return new ResponseEntity<>(service.update(boardId, replyId, replyUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{replyId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> removeReply(@RequestParam("boardId") int boardId, @PathVariable("replyId") int replyId, @RequestBody ReplyUpdateDto replyUpdateDto) {
        service.remove(boardId, replyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
