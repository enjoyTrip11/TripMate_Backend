package com.ssafy.tripmate.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReplySaveDto{");
        sb.append("boardId=").append(boardId);
        sb.append(", userId=").append(userId);
        sb.append(", replyUserId=").append(replyUserId);
        sb.append(", comment='").append(comment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
