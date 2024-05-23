package com.ssafy.tripmate.place.controller;

import com.ssafy.tripmate.place.dto.PlaceException;
import com.ssafy.tripmate.place.dto.PlaceResponseDto;
import com.ssafy.tripmate.place.dto.SearchFilter;
import com.ssafy.tripmate.place.service.PlaceService;
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
@RequestMapping("/place")
@Slf4j
@Tag(name = "Place 컨트롤러", description = "장소 정보 관련 클래스")
public class PlaceController {

    private final PlaceService service;

    public PlaceController(PlaceService service) {
        this.service = service;
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(PlaceException.class)
        public ResponseEntity<String> handleException(PlaceException e) {
            log.error("place.error >>> msg: {}", e.getMessage());

            HttpHeaders resHeader = new HttpHeaders();
            resHeader.add("Content-Type", "application/json;charset=UTF-8");
            return new ResponseEntity<>("place 처리 중 오류 발생:" + e.getMessage(), resHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping
//    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "장소 조회 성공"),
//            @ApiResponse(responseCode = "204", description = "장소 정보 없음"),
//            @ApiResponse(responseCode = "500", description = "서버 에러")})
//    public ResponseEntity<?> findPlaces(@ModelAttribute SearchFilter searchFilter) {
//        List<PlaceResponseDto> places = service.findAll(searchFilter);
//        if (places.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(places, HttpStatus.OK);
//    }

    @GetMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "장소 조회 성공"),
            @ApiResponse(responseCode = "204", description = "장소 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> findPlaces(
            @RequestParam("keyword") String keyword,
            @RequestParam(name = "sidoCode", defaultValue = "0") int sidoCode,
            @RequestParam(name = "contentTypeId", defaultValue = "0") int contentTypeId,
            @RequestParam(name = "latitude", defaultValue = "0") double latitude,
            @RequestParam(name = "longitude", defaultValue = "0") double longitude
    ) {

        SearchFilter searchFilter = new SearchFilter(keyword, sidoCode, contentTypeId, latitude, longitude);
        List<PlaceResponseDto> places = service.findAll(searchFilter);
        if (places.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(places, HttpStatus.OK);
    }
}
