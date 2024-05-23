package com.ssafy.tripmate.user.dto.response;

import com.ssafy.tripmate.user.dto.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AfterLoginResponse {
    private SignStatus signStatus;
    private TokenDto tokenDto;
}
