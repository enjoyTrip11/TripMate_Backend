package com.ssafy.tripmate.tripInvite.dto;


public class InviteSaveDto {
    private Integer tripId;
    private Integer receiverId;
    private String state;

    public Invite toEntity() {
        return Invite.builder()
                .tripId(tripId)
                .receiverId(receiverId)
                .state(state)
                .build();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InviteSaveDto{");
        sb.append("tripId=").append(tripId);
        sb.append(", receiverId='").append(receiverId).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
