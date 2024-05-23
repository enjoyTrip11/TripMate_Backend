package com.ssafy.tripmate.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    private String profile;
}
