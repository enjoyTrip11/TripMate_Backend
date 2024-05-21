package com.ssafy.tripmate.board.service;

import com.ssafy.tripmate.board.dto.Board;
import com.ssafy.tripmate.board.dto.BoardResponseDto;
import com.ssafy.tripmate.board.dto.BoardSaveDto;
import com.ssafy.tripmate.board.dto.BoardUpdateDto;

import java.util.List;

public interface BoardService {

    public List<BoardResponseDto> findAll();

    public List<BoardResponseDto> findAllByUserId(String category, int userId);

    public List<BoardResponseDto> findAllByKeyword(String category, String keyword);

    public List<BoardResponseDto> findAllByCategory(String category);

    public Board findByBoardId(int boardId);

    public void delete(int boardId);

    public int update(int boardId, BoardUpdateDto boardUpdateDto);

    public int insert(BoardSaveDto boardSaveDto);
}
