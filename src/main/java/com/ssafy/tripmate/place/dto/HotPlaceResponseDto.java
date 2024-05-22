package com.ssafy.tripmate.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HotPlaceResponseDto {
    private Integer hits;
    private PlaceResponseDto place;

    public HotPlaceResponseDto(Place place, int hits) {
        this.hits = hits;
        this.place = new PlaceResponseDto(place);
    }
}
