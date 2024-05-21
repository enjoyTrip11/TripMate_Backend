package com.ssafy.tripmate.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReplySaveDto {

    private Integer boardId;
    private Integer userId;
    private Integer replyUserId;
    String comment;

    public Reply toEntity() {
        return Reply.builder()
                .boardId(boardId)
                .userId(userId)
                .replyUserId(replyUserId)
                .comment(comment)
                .build();
    }
}
