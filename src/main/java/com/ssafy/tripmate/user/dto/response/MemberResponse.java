package com.ssafy.tripmate.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private Integer memberId;
    private String nickname;
    private String email;
    private String password;
}
