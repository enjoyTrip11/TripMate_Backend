package com.ssafy.tripmate.plan.dto;

import lombok.Getter;

@Getter
public class PlanResponseDto {
    private Integer planId;
    private Integer tripId;
    private Integer locationId;
    private String date;
    private Integer order;

    public PlanResponseDto(Plan plan) {
        this.planId = plan.getPlanId();
        this.tripId = plan.getTripId();
        this.locationId = plan.getLocationId();
        this.date = plan.getDate();
        this.order = plan.getOrder();
    }
}
