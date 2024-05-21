package com.ssafy.tripmate.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardUpdateDto {
    private String title;
    private String content;
    private String filePath;
    private String category;

    @Builder
    public BoardUpdateDto(String title, String content, String filePath, String category) {
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.category = category;
    }
}
