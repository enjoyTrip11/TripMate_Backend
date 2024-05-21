package com.ssafy.tripmate.tripInvite.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InviteUpdateDto {
    private String state;
    @Builder
    public InviteUpdateDto(String state) {
        this.state = state;
    }
}
