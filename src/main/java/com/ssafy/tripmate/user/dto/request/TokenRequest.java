package com.ssafy.tripmate.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    @NotBlank
    private String accessToken;
    @NotBlank
    private String refreshToken;
}
