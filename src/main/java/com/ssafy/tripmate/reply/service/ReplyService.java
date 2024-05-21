package com.ssafy.tripmate.reply.service;

import com.ssafy.tripmate.reply.dto.ReplyResponseDto;
import com.ssafy.tripmate.reply.dto.ReplySaveDto;
import com.ssafy.tripmate.reply.dto.ReplyUpdateDto;

import java.util.List;

public interface ReplyService {
    int create(ReplySaveDto replySaveDto);

    List<ReplyResponseDto> searchAll(int boardId);

    ReplyResponseDto search(int boardId, int replyId);

    int update(int boardId, int replyId, ReplyUpdateDto replyUpdateDto);

    void remove(int boardId, int replyId);
}
