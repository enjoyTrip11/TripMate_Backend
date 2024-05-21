package com.ssafy.tripmate.trip.controller;

import com.ssafy.tripmate.board.dto.*;
import com.ssafy.tripmate.board.service.BoardService;
import com.ssafy.tripmate.trip.dto.Trip;
import com.ssafy.tripmate.trip.dto.TripResponseDto;
import com.ssafy.tripmate.trip.dto.TripSaveDto;
import com.ssafy.tripmate.trip.dto.TripUpdateDto;
import com.ssafy.tripmate.trip.service.TripService;
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
@RequestMapping("/trip")
@Slf4j
@Tag(name = "Trip 컨트롤러", description = "여행 CRUD 관련 클래스")
public class TripController {

    private final TripService service;

    public TripController(TripService service) {
        this.service = service;
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
            log.error("board.error >>> msg: {}", e.getMessage());

            HttpHeaders resHeader = new HttpHeaders();
            resHeader.add("Content-Type", "application/json;charset=UTF-8");

            if (e instanceof BoardException) {
                return new ResponseEntity<>(e.getMessage(), resHeader, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Trip 처리 중 오류 발생", resHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "여행 목록 조회 성공"),
            @ApiResponse(responseCode = "204", description = "여행 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> loadTrip(
            @RequestParam(required = false) Integer userId) {
        List<TripResponseDto> trips = service.findAllByUserId(userId);
        if (trips.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/{tripId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "여행 목록 조회 성공"),
            @ApiResponse(responseCode = "204", description = "여행 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> loadTrip(@PathVariable("tripId") int tripId) {
        Trip trip = service.findByTripId(tripId);
        if (trip == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @DeleteMapping("/{tripId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "여행 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "여행 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> deleteTrip(@PathVariable("tripId") int tripId) {
        service.delete(tripId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "여행 생성 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> createTrip(@RequestBody TripSaveDto tripSaveDto) {
        log.debug("[TRIP]insert>>>>>>>>>>>", tripSaveDto);
        return new ResponseEntity<>(service.insert(tripSaveDto), HttpStatus.OK);
    }

    @PutMapping("/{tripId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "여행 정보 수정 성공"),
            @ApiResponse(responseCode = "400", description = "여행 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> updateBoard(@PathVariable("tripId") int tripId, @RequestBody TripUpdateDto tripUpdateDto) {
        return new ResponseEntity<>(service.update(tripId, tripUpdateDto), HttpStatus.OK);
    }
}
