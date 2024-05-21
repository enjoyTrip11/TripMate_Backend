package com.ssafy.tripmate.reply.service;

import com.ssafy.tripmate.reply.dao.ReplyDao;
import com.ssafy.tripmate.reply.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    private final ReplyDao dao;

    public ReplyServiceImpl(ReplyDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public int create(ReplySaveDto replySaveDto) {
        try {
            log.debug("[REPLY]insert DTO>>>>> {}", replySaveDto);
            Reply reply = replySaveDto.toEntity();
            log.debug("[REPLY]insert>>>>>> {}", reply);
            dao.insert(reply);
            return reply.getReplyId();
        } catch (SQLException e) {
            throw new ReplyException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<ReplyResponseDto> searchAll(int boardId) {
        try {
            List<Reply> replies = dao.searchAll(boardId);
            return replies.stream()
                    .map(ReplyResponseDto::new)
                    .toList();
        } catch (SQLException e) {
            throw new ReplyException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ReplyResponseDto search(int boardId, int replyId) {
        try {
            Reply reply = dao.search(boardId, replyId);
            return new ReplyResponseDto(reply);
        } catch (SQLException e) {
            throw new ReplyException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int update(int boardId, int replyId, ReplyUpdateDto replyUpdateDto) {
        try {
            Reply reply = dao.search(boardId, replyId);
            if (reply==null) {
                throw new ReplyException("존재하지 않는 댓글입니다");
            }
            reply.update(replyUpdateDto);
            dao.update(reply);
            return replyId;
        } catch (SQLException e) {
            throw new ReplyException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void remove(int boardId, int replyId) {
        try {
            Reply reply = dao.search(boardId, replyId);
            if (reply != null) {
                dao.remove(replyId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
