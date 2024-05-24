package com.ssafy.tripmate.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlanSaveDto {
    private Integer tripId;
    private Integer locationId;
    private String date;
    private Integer planOrder;

    public Plan toEntity() {
        return Plan.builder()
                .tripId(tripId)
                .locationId(locationId)
                .date(date)
                .planOrder(planOrder)
                .build();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlanSaveDto{");
        sb.append("tripId=").append(tripId);
        sb.append(", locationId=").append(locationId);
        sb.append(", date=").append(date);
        sb.append(", planOrder='").append(planOrder).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
