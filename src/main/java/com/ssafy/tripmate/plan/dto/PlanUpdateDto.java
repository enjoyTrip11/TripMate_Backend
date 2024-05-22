package com.ssafy.tripmate.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlanUpdateDto {
    private String date;
    private Integer planOrder;
}
