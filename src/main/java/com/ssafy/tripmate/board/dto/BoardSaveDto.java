package com.ssafy.tripmate.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardSaveDto {

    private int userId;
    private String title;
    private String content;
    private String filePath;
    private String category;

    public Board toEntity() {
        return Board.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .filePath(filePath)
                .category(category)
                .build();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BoardSaveDto{");
        sb.append("userId=").append(userId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", filePath='").append(filePath).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
