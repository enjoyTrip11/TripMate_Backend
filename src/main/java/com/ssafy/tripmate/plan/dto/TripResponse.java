package com.ssafy.tripmate.plan.dto;

import com.ssafy.tripmate.trip.dto.Trip;
import com.ssafy.tripmate.tripInvite.dto.InviteResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TripResponse {
    private Trip trip;
    private List<PlanResponseDto> plans;
    private List<InviteResponseDto> invites;
}
