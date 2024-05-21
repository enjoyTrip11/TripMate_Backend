package com.ssafy.tripmate.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class HotPlace {
    private Integer hotplaceId;
    private Integer locationId;
    private Integer userId;
}
