package com.ssafy.tripmate.place.service;

import com.ssafy.tripmate.place.dao.HotPlaceDao;
import com.ssafy.tripmate.place.dto.HotPlace;
import com.ssafy.tripmate.place.dto.HotPlaceException;
import com.ssafy.tripmate.place.dto.HotPlaceResponseDto;
import com.ssafy.tripmate.place.dto.Place;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class HotPlaceServiceImpl implements HotPlaceService {

    private final HotPlaceDao dao;

    public HotPlaceServiceImpl(HotPlaceDao dao) {
        this.dao = dao;
    }

    @Override
    public int regist(int boardId, int userId) {
        try {
            HotPlace hotPlace = dao.searchByBoardUser(boardId, userId);

            if (hotPlace != null) {
                throw new HotPlaceException("이미 등록된 핫플레이스입니다.");
            }
            dao.insert(boardId, userId);
            return hotPlace.getHotplaceId();
        } catch (SQLException e) {
            throw new HotPlaceException(e.getMessage());
        }
    }

    @Override
    public void remove(int hotplaceId) {
        try {
            HotPlace hotPlace = dao.searchById(hotplaceId);

            if (hotPlace != null) {
                dao.delete(hotplaceId);
            }
        } catch (SQLException e) {
            throw new HotPlaceException(e.getMessage());
        }
    }

    @Override
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
    public List<HotPlaceResponseDto> sortAllHotPlacesByHits() {
        try {
            return dao.searchAllHotPlace().stream()
                    .sorted(Comparator.comparing(HotPlaceResponseDto::getHits).reversed()) // hits를 기준으로 내림차순 정렬
                    .toList();
        } catch (SQLException e) {
            throw new HotPlaceException(e.getMessage());
        }
    }
}
