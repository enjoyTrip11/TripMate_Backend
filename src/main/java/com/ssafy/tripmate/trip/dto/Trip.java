package com.ssafy.tripmate.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Trip implements Serializable {
    private Integer tripId;
    private Integer writer;
    private String title;
    private String place;
    private String img;
    private String startDate;
    private String endDate;

    public void update(TripUpdateDto tripUpdateDto) {
        this.title = tripUpdateDto.getTitle();
        this.place = tripUpdateDto.getPlace();
        this.img = tripUpdateDto.getImg();
        this.startDate = tripUpdateDto.getStartDate();
        this.endDate = tripUpdateDto.getEndDate();
    }
}
