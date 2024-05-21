package com.ssafy.tripmate.trip.controller;

import com.ssafy.tripmate.board.dto.*;
import com.ssafy.tripmate.plan.dto.PlanResponseDto;
import com.ssafy.tripmate.plan.dto.PlanSaveDto;
import com.ssafy.tripmate.plan.dto.TripResponse;
import com.ssafy.tripmate.plan.service.PlanService;
import com.ssafy.tripmate.trip.dto.Trip;
import com.ssafy.tripmate.trip.dto.TripResponseDto;
import com.ssafy.tripmate.trip.dto.TripSaveDto;
import com.ssafy.tripmate.trip.dto.TripUpdateDto;
import com.ssafy.tripmate.trip.service.TripService;
import com.ssafy.tripmate.tripInvite.dto.InviteResponseDto;
import com.ssafy.tripmate.tripInvite.dto.InviteSaveDto;
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
@RequestMapping("/trip")
@Slf4j
@Tag(name = "Trip 컨트롤러", description = "여행 CRUD 관련 클래스")
public class TripController {

    private final TripService tripService;
    private final PlanService planService;
    private final InviteService inviteService;

    public TripController(TripService tripService, PlanService planService, InviteService inviteService) {
        this.tripService = tripService;
        this.planService = planService;
        this.inviteService = inviteService;
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
        List<TripResponseDto> trips = tripService.findAllByUserId(userId);
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
        Trip trip = tripService.findByTripId(tripId);
        List<PlanResponseDto> plans = planService.searchAll(tripId);
        List<InviteResponseDto> invites = inviteService.findAllByTripId(tripId);
        TripResponse tripResponse = new TripResponse(trip, plans, invites);
        if (trip == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tripResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{tripId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "여행 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "여행 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> deleteTrip(@PathVariable("tripId") int tripId) {
        tripService.delete(tripId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "여행 생성 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> createTrip(@RequestBody TripSaveDto tripSaveDto, @RequestBody List<InviteSaveDto> inviteSaveDtos) {
        log.debug("[TRIP]insert>>>>>>>>>>>", tripSaveDto);
        for (InviteSaveDto inviteSaveDto : inviteSaveDtos) {
            inviteService.insert(inviteSaveDto);
        }
        return new ResponseEntity<>(tripService.insert(tripSaveDto), HttpStatus.OK);
    }

    @PutMapping("/{tripId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "여행 정보 수정 성공"),
            @ApiResponse(responseCode = "400", description = "여행 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> updateBoard(@PathVariable("tripId") int tripId, @RequestBody TripUpdateDto tripUpdateDto, @RequestBody List<InviteSaveDto> inviteSaveDtos, @RequestBody List<PlanSaveDto> planSaveDtos) {
        List<PlanResponseDto> plans = planService.searchAll(tripId);
        for (PlanResponseDto plan : plans) {
            planService.remove(plan.getPlanId());
        }
        for (PlanSaveDto planSaveDto : planSaveDtos) {
            planService.create(planSaveDto);
        }
        for (InviteSaveDto inviteSaveDto : inviteSaveDtos) {
            inviteService.insert(inviteSaveDto);
        }
        return new ResponseEntity<>(tripService.update(tripId, tripUpdateDto), HttpStatus.OK);
    }
}
