package com.ssafy.tripmate.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardUpdateDto {
    private String title;
    private String contents;
    private String filePath;
    private String category;

    @Builder
    public BoardUpdateDto(String title, String contents, String filePath, String category) {
        this.title = title;
        this.contents = contents;
        this.filePath = filePath;
        this.category = category;
    }
}
