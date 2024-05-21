package com.ssafy.tripmate.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchFilter {
    private String keyword;
    private Integer sidoCode;
    private Integer contentTypeId;
}
