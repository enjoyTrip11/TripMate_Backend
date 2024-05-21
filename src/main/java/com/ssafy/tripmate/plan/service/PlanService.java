package com.ssafy.tripmate.plan.service;

import com.ssafy.tripmate.plan.dto.PlanResponseDto;
import com.ssafy.tripmate.plan.dto.PlanSaveDto;
import com.ssafy.tripmate.plan.dto.PlanUpdateDto;

import java.util.List;

public interface PlanService {
    int create(PlanSaveDto planSaveDto);

    List<PlanResponseDto> searchAll(int tripId);

    PlanResponseDto search(int planId);

    int update(int planId, PlanUpdateDto planUpdateDto);

    void remove(int planId);
}
