package com.ssafy.tripmate.place.service;

import com.ssafy.tripmate.place.dao.HotPlaceDao;
import com.ssafy.tripmate.place.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class HotPlaceServiceImpl implements HotPlaceService {

    private final HotPlaceDao dao;

    public HotPlaceServiceImpl(HotPlaceDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public int regist(int locationId, int userId) {
        try {
            HotPlace hotPlace = dao.searchByLocUser(locationId, userId);
            if (hotPlace != null) {
//                throw new HotPlaceException("이미 등록된 핫플레이스입니다.");
                return 0;
            }
            dao.insert(locationId, userId);
            return dao.searchByLocUser(locationId, userId).getHotplaceId();
        } catch (SQLException e) {
            throw new HotPlaceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void remove(int locationId, int userId) {
        try {
            HotPlace hotPlace = dao.searchByLocUser(locationId, userId);
            log.debug("[HOTPLACE] 지울 Hotplace:" + hotPlace + ", id:" + hotPlace.getHotplaceId());
            if (hotPlace != null) {
                dao.delete(hotPlace.getHotplaceId());
            }
        } catch (SQLException e) {
            throw new HotPlaceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<HotPlaceResponseDto> sortUserHotPlacesByHits(int userId) {
        try {
            List<Place> places = dao.searchHotPlaceByUser(userId);

            // 조회수를 기준으로 장소를 정렬하는 Comparator 정의
            Comparator<Place> byHits = Comparator.comparingInt(place -> {
                try {
                    return dao.countHotPlaceHits(place.getLocationId());
                } catch (SQLException e) {
                    throw new HotPlaceException(e.getMessage());
                }
            });

            // 정렬된 장소 목록 반환
            return places.stream()
                    .sorted(byHits.reversed()) // 내림차순 정렬
                    .map(place -> {
                        int hits;
                        try {
                            hits = dao.countHotPlaceHits(place.getLocationId());
                        } catch (SQLException e) {
                            throw new HotPlaceException(e.getMessage());
                        }
                        return new HotPlaceResponseDto(place, hits);
                    })
                    .toList();
        } catch (SQLException e) {
            throw new HotPlaceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<HotPlaceResponseDto> sortAllHotPlacesByHits(int userId) {
        try {
            List<Map<String, String>> results = dao.searchAllHotPlace(userId);
            List<HotPlaceResponseDto> places = new ArrayList<>(10);
            for (Map<String, String> result : results) {
                int hits = Integer.parseInt(result.get("hits"));
                PlaceResponseDto placeResponseDto = PlaceResponseDto.builder()
                        .isFavorite(Boolean.parseBoolean(result.get("isFavorite")))
                        .locationId(Integer.parseInt(result.get("locationId")))
                        .title(result.get("title"))
                        .addr1(result.get("addr1"))
                        .addr2(result.get("addr2"))
                        .zipcode(result.get("zipcode"))
                        .firstImage(result.get("firstImage"))
                        .secondImage(result.get("secondImage"))
                        .sidoCode(Integer.parseInt(result.get("sidoCode")))
                        .latitude(Double.parseDouble(result.get("latitude")))
                        .longitude(Double.parseDouble(result.get("longitude")))
                        .overview(result.get("overview"))
                        .contentTypeId(Integer.parseInt(result.get("contentTypeId")))
                        .build();

                places.add(new HotPlaceResponseDto(hits, placeResponseDto));
            }
            return places.stream()
                    .sorted(Comparator.comparing(HotPlaceResponseDto::getHits).reversed()) // hits를 기준으로 내림차순 정렬
                    .toList();
        } catch (SQLException e) {
            throw new HotPlaceException(e.getMessage());
        }
    }
}
