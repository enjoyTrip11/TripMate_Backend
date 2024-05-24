package com.ssafy.tripmate.place.service;

import com.ssafy.tripmate.place.dto.PlaceResponseDto;
import com.ssafy.tripmate.place.dto.SearchFilter;

import java.util.List;

public interface PlaceService {
    List<PlaceResponseDto> findAll(SearchFilter searchFilter);
    PlaceResponseDto getPlaceById(Integer locationId);
}
