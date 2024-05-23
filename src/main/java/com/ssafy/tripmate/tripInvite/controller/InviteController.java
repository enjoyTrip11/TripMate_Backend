package com.ssafy.tripmate.tripInvite.controller;

import com.ssafy.tripmate.board.dto.*;
import com.ssafy.tripmate.tripInvite.dto.*;
import com.ssafy.tripmate.tripInvite.service.InviteService;
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
@RequestMapping("/invite")
@Slf4j
@Tag(name = "Invite 컨트롤러", description = "친구 CRUD 관련 클래스")
public class InviteController {

    private final InviteService service;

    public InviteController(InviteService service) {
        this.service = service;
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {


        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
            log.error("invite.error >>> msg: {}", e.getMessage());

            HttpHeaders resHeader = new HttpHeaders();
            resHeader.add("Content-Type", "application/json;charset=UTF-8");

            if (e instanceof InviteException) {
                return new ResponseEntity<>(e.getMessage(), resHeader, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>("초대 처리 중 오류 발생", resHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{inviteId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "친구 조회 성공"),
            @ApiResponse(responseCode = "204", description = "친구 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> loadInvite(@PathVariable("inviteId") int inviteId) {
        Invite invite = service.searchByInviteId(inviteId);
        if (invite == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(invite, HttpStatus.OK);
    }

    @DeleteMapping("/{inviteId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "친구 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "게시물 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> deleteInvite(@PathVariable("inviteId") int inviteId) {
        service.delete(inviteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "친구 요청 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> createInvite(@RequestBody InviteSaveDto inviteSaveDto) {
        log.debug("[INVITE]insert>>>>>>>>>>>", inviteSaveDto);
        return new ResponseEntity<>(service.insert(inviteSaveDto), HttpStatus.OK);
    }

    @PutMapping("/{inviteId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "친구 수정 성공"),
            @ApiResponse(responseCode = "400", description = "친구 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> updateInvite(@PathVariable("inviteId") int inviteId, @RequestBody InviteUpdateDto inviteUpdateDto) {
        return new ResponseEntity<>(service.update(inviteId, inviteUpdateDto), HttpStatus.OK);
    }
}
