package com.ssafy.tripmate.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private Integer userId;
    private String name;
    private String id;
    private String password;
    private String profile;
}
