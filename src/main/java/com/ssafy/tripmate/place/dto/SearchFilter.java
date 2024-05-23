package com.ssafy.tripmate.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchFilter {

    private Integer userId;
    private String keyword;
    private Integer sidoCode;
    private Integer contentTypeId;

    // 정렬을 위한 위치 정보
    private Double latitude;
    private Double longitude;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchFilter{");
        sb.append("userId=").append(userId);
        sb.append(", keyword='").append(keyword).append('\'');
        sb.append(", sidoCode=").append(sidoCode);
        sb.append(", contentTypeId=").append(contentTypeId);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }
}
