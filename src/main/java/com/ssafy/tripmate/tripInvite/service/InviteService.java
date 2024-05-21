package com.ssafy.tripmate.tripInvite.service;


import com.ssafy.tripmate.tripInvite.dto.Invite;
import com.ssafy.tripmate.tripInvite.dto.InviteResponseDto;
import com.ssafy.tripmate.tripInvite.dto.InviteSaveDto;
import com.ssafy.tripmate.tripInvite.dto.InviteUpdateDto;

import java.util.List;

public interface InviteService {
    public List<InviteResponseDto> findAllByTripId(int tripId);

    public Invite searchByInviteId(int inviteId);

    public void delete(int inviteId);

    public int update(int inviteId, InviteUpdateDto inviteUpdateDto);

    public int insert(InviteSaveDto inviteSaveDto);
}
