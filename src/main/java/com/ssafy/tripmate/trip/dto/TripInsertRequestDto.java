package com.ssafy.tripmate.trip.dto;

import com.ssafy.tripmate.plan.dto.PlanSaveDto;
import com.ssafy.tripmate.tripInvite.dto.InviteSaveDto;

import java.util.List;

public class TripInsertRequestDto {
    private TripSaveDto tripSaveDto;
    private List<InviteSaveDto> inviteSaveDtoList;

    public TripSaveDto getTripSaveDto() {
        return tripSaveDto;
    }

    public void setTripSaveDto(TripSaveDto tripSaveDto) {
        this.tripSaveDto = tripSaveDto;
    }

    public List<InviteSaveDto> getInviteSaveDtoList() {
        return inviteSaveDtoList;
    }

    public void setInviteSaveDtoList(List<InviteSaveDto> inviteSaveDtoList) {
        this.inviteSaveDtoList = inviteSaveDtoList;
    }
}
