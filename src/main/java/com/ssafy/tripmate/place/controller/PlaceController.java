package com.ssafy.tripmate.place.controller;

import com.ssafy.tripmate.place.dto.PlaceResponseDto;
import com.ssafy.tripmate.place.dto.SearchFilter;
import com.ssafy.tripmate.place.service.PlaceService;
import com.ssafy.tripmate.place.service.PlaceServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.process.internal.UserTypeResolution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "장소 조회 성공"),
            @ApiResponse(responseCode = "204", description = "장소 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> findPlaces(@RequestBody SearchFilter searchFilter) {
        List<PlaceResponseDto> places = service.findAll(searchFilter);
        if (places.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(places, HttpStatus.OK);
    }
}
