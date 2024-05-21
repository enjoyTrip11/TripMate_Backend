package com.ssafy.tripmate.place.service;

import com.ssafy.tripmate.place.dao.PlaceDao;
import com.ssafy.tripmate.place.dto.Place;
import com.ssafy.tripmate.place.dto.PlaceException;
import com.ssafy.tripmate.place.dto.PlaceResponseDto;
import com.ssafy.tripmate.place.dto.SearchFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {

    private final PlaceDao dao;

    public PlaceServiceImpl(PlaceDao dao) {
        this.dao = dao;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("place.error >>> msg: {}", e.getMessage());

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");

        if (e instanceof PlaceException) {
            return new ResponseEntity<>(e.getMessage(), resHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Place 처리 중 오류 발생", resHeader, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<PlaceResponseDto> findAll(SearchFilter searchFilter) {
        try {
            double userLat = searchFilter.getLatitude();
            double userLon = searchFilter.getLongitude();

            List<Place> places = sortByHaversine(dao.searchAll(searchFilter), userLat, userLon);
            return places.stream()
                    .map(PlaceResponseDto::new)
                    .toList();
        } catch (SQLException e) {
            throw new PlaceException(e.getMessage());
        }
    }

    public List<Place> sortByHaversine(List<Place> places, double userLatitude, double userLongitude) {
        try {
            Collections.sort(places, (o1, o2) -> Double.compare(
                    distanceInKilometerByHaversine(o1.getLatitude(), o1.getLongitude(), userLatitude, userLongitude),
                    distanceInKilometerByHaversine(o2.getLatitude(), o2.getLongitude(), userLatitude, userLongitude)));
        } catch (NumberFormatException e) {
            log.debug("위도,경도 입력값 형식 오류: {}", e.getMessage());
        } catch (Exception e) {
            log.debug("Harversine 거리 기반 정렬 로직 수행 중 오류 발생: {}", e.getMessage());
        }
        return places;
    }

    /**
     * 사용자와 장소 간 Harversine 거리를 구하기 위한 함수
     */
    public static double distanceInKilometerByHaversine(double lat1, double lon1, double lat2, double lon2) {
        double distance;
        double radius = 6371; // 지구 반지름(km)
        double toRadian = Math.PI / 180;

        double deltaLatitude = Math.abs(lat1 - lat2) * toRadian;
        double deltaLongitude = Math.abs(lon1 - lon2) * toRadian;

        double sinDeltaLat = Math.sin(deltaLatitude / 2);
        double sinDeltaLng = Math.sin(deltaLongitude / 2);
        double squareRoot = Math.sqrt(sinDeltaLat * sinDeltaLat
                + Math.cos(lat1 * toRadian) * Math.cos(lat2 * toRadian) * sinDeltaLng * sinDeltaLng);

        distance = 2 * radius * Math.asin(squareRoot);

        return distance;
    }
}
