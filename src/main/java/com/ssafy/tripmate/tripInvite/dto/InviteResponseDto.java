package com.ssafy.tripmate.tripInvite.dto;

import com.ssafy.tripmate.board.dto.Board;
import lombok.Getter;

@Getter
public class InviteResponseDto {
    private Integer tripId;
    private Integer receiverId;
    private String state;

    public InviteResponseDto(Invite invite) {
        this.tripId = invite.getTripId();
        this.receiverId = invite.getReceiverId();
        this.state = invite.getState();
    }
}
