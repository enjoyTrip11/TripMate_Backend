package com.ssafy.tripmate.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TripSaveDto {
    private Integer writer;
    private String title;
    private String place;
    private String img;
    private String startDate;
    private String endDate;

    public Trip toEntity() {
        return Trip.builder()
                .writer(writer)
                .title(title)
                .place(place)
                .img(img)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TripSaveDto{");
        sb.append("writer=").append(writer);
        sb.append(", title='").append(title).append('\'');
        sb.append(", place='").append(place).append('\'');
        sb.append(", img='").append(img).append('\'');
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
