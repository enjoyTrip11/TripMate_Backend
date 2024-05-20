package com.ssafy.tripmate.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Board implements Serializable {
    private int boardId;
    private int userId;
    private String title;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String filePath;
    private String category;

    public void update(BoardUpdateDto boardUpdateDto) {
        this.title = boardUpdateDto.getTitle();
        this.contents = boardUpdateDto.getContents();
        this.category = boardUpdateDto.getCategory();
        this.filePath = boardUpdateDto.getFilePath();
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", filePath='" + filePath + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

