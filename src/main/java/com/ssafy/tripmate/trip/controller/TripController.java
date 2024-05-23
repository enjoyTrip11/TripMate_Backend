package com.ssafy.tripmate.trip.controller;

import com.ssafy.tripmate.plan.dto.PlanResponseDto;
import com.ssafy.tripmate.plan.dto.PlanSaveDto;
import com.ssafy.tripmate.plan.dto.TripResponse;
import com.ssafy.tripmate.plan.service.PlanService;
import com.ssafy.tripmate.trip.dto.*;
import com.ssafy.tripmate.trip.service.TripService;
import com.ssafy.tripmate.tripInvite.dto.InviteResponseDto;
import com.ssafy.tripmate.tripInvite.dto.InviteSaveDto;
import com.ssafy.tripmate.tripInvite.service.InviteService;
import com.ssafy.tripmate.user.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    private final UserService userService;

    public TripController(TripService tripService, PlanService planService, InviteService inviteService, UserService userService) {
        this.tripService = tripService;
        this.planService = planService;
        this.inviteService = inviteService;
        this.userService = userService;
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(TripException.class)
        public ResponseEntity<String> handleException(Exception e) {
            log.error("trip.error >>> msg: {}", e.getMessage());

            HttpHeaders resHeader = new HttpHeaders();
            resHeader.add("Content-Type", "application/json;charset=UTF-8");

            if (e instanceof TripException) {
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

    @Transactional
    @PostMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "여행 생성 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> createTrip(@RequestBody TripInsertRequestDto tripRequestDto) {
        TripSaveDto tripSaveDto = tripRequestDto.getTripSaveDto();
        tripSaveDto.setWriter(userService.getMemberByJwt().getUserId());
        System.out.println("HERE_____________________");
        System.out.println(tripSaveDto.getWriter());
        int tripId = tripService.insert(tripSaveDto);
                List<InviteSaveDto> inviteSaveDtos = tripRequestDto.getInviteSaveDtoList();
        log.debug("[TRIP]insert>>>>>>>>>>>", tripSaveDto);
        for (InviteSaveDto inviteSaveDto : inviteSaveDtos) {
            inviteSaveDto.setTripId(tripId);
            inviteSaveDto.setState("PENDING");
            inviteService.insert(inviteSaveDto);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{tripId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "여행 정보 수정 성공"),
            @ApiResponse(responseCode = "400", description = "여행 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<Integer> updateTrip(@PathVariable("tripId") int tripId, @RequestBody TripUpdateRequestDto tripRequestDto) {
        TripUpdateDto tripUpdateDto = tripRequestDto.getTripUpdateDto();
        List<InviteSaveDto> inviteSaveDtos = tripRequestDto.getInviteSaveDtoList();
        List<PlanSaveDto> planSaveDtos = tripRequestDto.getPlanSaveDtoList();
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
