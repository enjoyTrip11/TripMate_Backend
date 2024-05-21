package com.ssafy.tripmate.reply.dto;

import lombok.Getter;

@Getter
public class ReplyResponseDto {
    private Integer boardId;
    private Integer userId;
    private Integer replyUserId;
    String comment;

    public ReplyResponseDto(Reply reply) {
        this.boardId = reply.getBoardId();
        this.userId = reply.getUserId();
        this.replyUserId = reply.getReplyUserId();
        this.comment = reply.getComment();
    }
}
