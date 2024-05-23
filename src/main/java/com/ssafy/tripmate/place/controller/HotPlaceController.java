package com.ssafy.tripmate.place.controller;

import com.ssafy.tripmate.place.dto.HotPlaceException;
import com.ssafy.tripmate.place.dto.HotPlaceResponseDto;
import com.ssafy.tripmate.place.service.HotPlaceService;
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
@RequestMapping("/hotplace")
@Slf4j
@Tag(name = "HotPlace 컨트롤러", description = "핫플레이스 정보 관련 클래스")
public class HotPlaceController {

    private final HotPlaceService service;

    public HotPlaceController(HotPlaceService service) {
        this.service = service;
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(HotPlaceException.class)
        public ResponseEntity<String> handleException(HotPlaceException e) {
            log.error("hotplace.error >>> msg: {}", e.getMessage());

            HttpHeaders resHeader = new HttpHeaders();
            resHeader.add("Content-Type", "application/json;charset=UTF-8");
            return new ResponseEntity<>("hotplace 처리 중 오류 발생:" + e.getMessage(), resHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "핫플레이스 조회 성공"),
            @ApiResponse(responseCode = "204", description = "핫플레이스 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> findAllHotPlaces(@RequestParam("userId") int userId) {
        List<HotPlaceResponseDto> hotPlaces = service.sortAllHotPlacesByHits(userId);
        if (hotPlaces.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hotPlaces, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "유저별 핫플레이스 조회 성공"),
            @ApiResponse(responseCode = "204", description = "유저 핫플레이스 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> findUserHotPlaces(@PathVariable("userId") int userId) {
        List<HotPlaceResponseDto> hotPlaces = service.sortUserHotPlacesByHits(userId);
        if (hotPlaces.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hotPlaces, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "핫플레이스 등록 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> registHotPlace(@RequestParam("locationId") int locationId,
                                            @RequestParam("userId") int userId) {
        return new ResponseEntity<>(service.regist(locationId, userId), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "핫플레이스 삭제 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> removeHotPlace(@RequestParam("locationId") int locationId,
                                            @RequestParam("userId") int userId) {
        service.remove(locationId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
