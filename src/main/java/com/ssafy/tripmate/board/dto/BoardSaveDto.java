package com.ssafy.tripmate.board.dto;

public class BoardSaveDto {

    private int userId;
    private String title;
    private String contents;
    private String filePath;
    private String category;

    public Board toEntity() {
        return Board.builder()
                .userId(userId)
                .title(title)
                .contents(contents)
                .filePath(filePath)
                .category(category)
                .build();
    }
}
