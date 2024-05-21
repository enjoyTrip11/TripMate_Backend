package com.ssafy.tripmate.place.service;

import com.ssafy.tripmate.place.dto.HotPlaceResponseDto;

import java.util.List;

public interface HotPlaceService {
    int regist(int boardId, int userId);

    void remove(int hotplaceId);

    List<HotPlaceResponseDto> sortUserHotPlacesByHits(int userId);

    List<HotPlaceResponseDto> sortAllHotPlacesByHits();
}
