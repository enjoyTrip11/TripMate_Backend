package com.ssafy.tripmate.board.service;

import com.ssafy.tripmate.board.dto.Board;
import com.ssafy.tripmate.board.dto.BoardResponseDto;
import com.ssafy.tripmate.board.dto.BoardSaveDto;
import com.ssafy.tripmate.board.dto.BoardUpdateDto;

import java.util.List;

public interface BoardService {

    public List<BoardResponseDto> searchAll();

    public List<BoardResponseDto> searchAllByUserId(String category, int userId);

    public List<BoardResponseDto> searchAllByKeyword(String category, String keyword);

    public List<BoardResponseDto> searchAllByCategory(String category);

    public Board searchByBoardId(int boardId);

    public int remove(int boardId);

    public int update(int boardId, BoardUpdateDto boardUpdateDto);

    public int insert(int userId, BoardSaveDto boardSaveDto);
}
