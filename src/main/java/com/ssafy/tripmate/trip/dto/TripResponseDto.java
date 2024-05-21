package com.ssafy.tripmate.trip.dto;

public class TripResponseDto {
    private Integer tripId;
    private Integer writer;
    private String title;
    private String place;
    private String img;
    private String startDate;
    private String endDate;

    public TripResponseDto(Trip trip) {
        this.tripId = trip.getTripId();
        this.writer = trip.getWriter();
        this.title = trip.getTitle();
        this.place = trip.getPlace();
        this.img = trip.getImg();
        this.startDate = trip.getStartDate();
        this.endDate = trip.getEndDate();
    }
}
