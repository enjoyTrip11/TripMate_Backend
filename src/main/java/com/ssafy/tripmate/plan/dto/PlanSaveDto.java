package com.ssafy.tripmate.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlanSaveDto {
    private Integer tripId;
    private Integer locationId;
    private String date;
    private Integer order;

    public Plan toEntity() {
        return Plan.builder()
                .tripId(tripId)
                .locationId(locationId)
                .date(date)
                .order(order)
                .build();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlanSaveDto{");
        sb.append("tripId=").append(tripId);
        sb.append(", locationId=").append(locationId);
        sb.append(", date=").append(date);
        sb.append(", order='").append(order).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
