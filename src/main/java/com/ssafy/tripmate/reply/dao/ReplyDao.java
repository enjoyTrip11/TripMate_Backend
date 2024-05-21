package com.ssafy.tripmate.reply.dao;

import com.ssafy.tripmate.reply.dto.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ReplyDao {

    int insert(Reply reply) throws SQLException;

    List<Reply> searchAll(int boardId) throws SQLException;

    Reply search(int boardId, int replyId) throws SQLException;

    void update(Reply reply) throws SQLException;

    void remove(int replyId) throws SQLException;
}
