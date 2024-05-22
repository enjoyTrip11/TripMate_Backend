package com.ssafy.tripmate.place.service;

import com.ssafy.tripmate.place.dto.HotPlaceResponseDto;

import java.util.List;

public interface HotPlaceService {
    int regist(int locationId, int userId);

    void remove(int locationId, int userId);

    List<HotPlaceResponseDto> sortUserHotPlacesByHits(int userId);

    List<HotPlaceResponseDto> sortAllHotPlacesByHits();
}
