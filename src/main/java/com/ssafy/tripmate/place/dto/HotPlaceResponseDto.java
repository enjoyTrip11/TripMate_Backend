package com.ssafy.tripmate.place.dto;

import lombok.Getter;

@Getter
public class HotPlaceResponseDto {
    private Integer hits;
    private PlaceResponseDto place;

    public HotPlaceResponseDto(Place place, int hits) {
        this.hits = hits;
        this.place = new PlaceResponseDto(place);
    }
}
