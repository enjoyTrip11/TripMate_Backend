package com.ssafy.tripmate.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PlaceResponseDto {
    private Integer locationId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String firstImage;
    private String secondImage;
    private Integer sidoCode;
    private Double latitude;
    private Double longitude;
    private String overview;
    private Integer contentTypeId;

    public PlaceResponseDto(Place place) {
        this.locationId = place.getLocationId();
        this.title = place.getTitle();
        this.addr1 = place.getAddr1();
        this.addr2 = place.getAddr2();
        this.zipcode = place.getZipcode();
        this.firstImage = place.getFirstImage();
        this.secondImage = place.getSecondImage();
        this.sidoCode = place.getSidoCode();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.overview = place.getOverview();
        this.contentTypeId = place.getContentTypeId();
    }
}
