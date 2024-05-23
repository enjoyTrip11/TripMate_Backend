package com.ssafy.tripmate.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    @NotBlank
    private String accessToken;
    @NotBlank
    private String refreshToken;
}
