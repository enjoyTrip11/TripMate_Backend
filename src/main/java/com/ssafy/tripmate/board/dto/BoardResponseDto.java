package com.ssafy.tripmate.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private int boardId;
    private int userId;
    private String title;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String filePath;
    private String category;
}
