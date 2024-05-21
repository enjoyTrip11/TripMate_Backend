package com.ssafy.tripmate.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Plan {
    private Integer planId;
    private Integer tripId;
    private Integer locationId;
    private String date;
    private Integer order;

    public void update(PlanUpdateDto planUpdateDto) {
        this.date = planUpdateDto.getDate();
        this.order = planUpdateDto.getOrder();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Plan{");
        sb.append("planId=").append(planId);
        sb.append(", tripId=").append(tripId);
        sb.append(", locationId=").append(locationId);
        sb.append(", date=").append(date);
        sb.append(", order='").append(order).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
