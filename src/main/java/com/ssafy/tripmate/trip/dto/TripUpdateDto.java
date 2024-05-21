package com.ssafy.tripmate.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TripUpdateDto {
    private String title;
    private String place;
    private String img;
    private String startDate;
    private String endDate;

    @Builder
    public TripUpdateDto(String title, String place, String img, String startDate, String endDate) {
        this.title = title;
        this.place = place;
        this.img = img;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
