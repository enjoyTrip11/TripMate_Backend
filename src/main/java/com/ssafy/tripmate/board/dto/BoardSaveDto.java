package com.ssafy.tripmate.board.dto;

import java.time.LocalDateTime;

public class BoardSaveDto {
    private String title;
    private String contents;
    private LocalDateTime createDate;
    private String filePath;
    private String category;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .contents(contents)
                .createDate(createDate)
                .filePath(filePath)
                .category(category)
                .build();
    }
}
