package com.ssafy.tripmate.reply.dto;

import lombok.Getter;

@Getter
public class ReplyResponseDto {

    private Integer replyId;
    private Integer boardId;
    private Integer userId;
    private Integer replyUserId;
    String comment;

    public ReplyResponseDto(Reply reply) {
        this.replyId = reply.getReplyId();
        this.boardId = reply.getBoardId();
        this.userId = reply.getUserId();
        this.replyUserId = reply.getReplyUserId();
        this.comment = reply.getComment();
    }
}
