package com.ssafy.tripmate.reply.dto;

import com.ssafy.tripmate.board.dto.BoardUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Reply {
    int replyId;
    int boardId;
    int userId;
    int replyUserId;
    String comment;

    public void update(BoardUpdateDto boardUpdateDto) {
        this.comment = boardUpdateDto.getContents();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reply{");
        sb.append("replyId=").append(replyId);
        sb.append(", boardId=").append(boardId);
        sb.append(", userId=").append(userId);
        sb.append(", replyUserId=").append(replyUserId);
        sb.append(", comment='").append(comment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
