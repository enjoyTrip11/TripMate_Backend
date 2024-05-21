package com.ssafy.tripmate.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Place {
    private Integer locationId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String firstImage;
    private String secondImage;
    private Integer sidoCode;
    private Integer gugunCode;
    private Double latitude;
    private Double longitude;
    private String overview;
    private Integer contentTypeId;
}
