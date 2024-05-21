package com.ssafy.tripmate.tripInvite.dto;

import com.ssafy.tripmate.board.dto.BoardUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Invite {
    private Integer inviteId;
    private Integer tripId;
    private Integer receiverId;
    private String state;

    public void update(InviteUpdateDto inviteUpdateDto) {
        this.state = inviteUpdateDto.getState();
    }

    @Override
    public String toString() {
        return "Invite{" +
                "inviteId=" + inviteId +
                ", tripId=" + tripId +
                ", receiverId='" + receiverId + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
