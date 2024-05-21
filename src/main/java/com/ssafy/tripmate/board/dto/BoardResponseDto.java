package com.ssafy.tripmate.board.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    private int boardId;
    private int userId;
    private String title;
    private String contents;
    private LocalDateTime modifyDate;
    private String filePath;
    private String category;

    public BoardResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.userId = board.getUserId();
        this.title = board.getTitle();
        this.contents = board.getContent();
        this.modifyDate = board.getModifyDate();
        this.filePath = board.getFilePath();
        this.category = board.getCategory();
    }
}
