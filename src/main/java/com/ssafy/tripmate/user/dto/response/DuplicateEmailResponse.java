package com.ssafy.tripmate.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DuplicateEmailResponse {
    private Boolean isPresent;
}
