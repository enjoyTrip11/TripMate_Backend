package com.ssafy.tripmate.board.dao;

import com.ssafy.tripmate.board.dto.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BoardDao {
    public List<Board> searchAll(String category, int userId, String keyword) throws SQLException;

    public List<Board> searchAllByUserId(String category, int userId) throws SQLException;

    public List<Board> searchAllByKeyword(String category, String keyword) throws SQLException;

    public List<Board> searchAllByCategory(String category) throws SQLException;

    public Board searchByBoardId(int boardId) throws SQLException;

    public void remove(int boardId) throws SQLException;

    public void update(Board board) throws SQLException;

    public int insert(Board board) throws SQLException;

}
