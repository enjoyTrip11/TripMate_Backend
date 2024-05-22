package com.ssafy.tripmate.trip.dto;

import com.ssafy.tripmate.plan.dto.PlanSaveDto;
import com.ssafy.tripmate.tripInvite.dto.InviteSaveDto;

import java.util.List;

public class TripUpdateRequestDto {
    private List<InviteSaveDto> inviteSaveDtoList;
    private TripUpdateDto tripUpdateDto;
    private List<PlanSaveDto> planSaveDtoList;

    public TripUpdateDto getTripUpdateDto() {
        return tripUpdateDto;
    }

    public void setTripUpdateDto(TripUpdateDto tripUpdateDto) {
        this.tripUpdateDto = tripUpdateDto;
    }

    public List<PlanSaveDto> getPlanSaveDtoList() {
        return planSaveDtoList;
    }

    public void setPlanSaveDtoList(List<PlanSaveDto> planSaveDtoList) {
        this.planSaveDtoList = planSaveDtoList;
    }

    public List<InviteSaveDto> getInviteSaveDtoList() {
        return inviteSaveDtoList;
    }

    public void setInviteSaveDtoList(List<InviteSaveDto> inviteSaveDtoList) {
        this.inviteSaveDtoList = inviteSaveDtoList;
    }
}
