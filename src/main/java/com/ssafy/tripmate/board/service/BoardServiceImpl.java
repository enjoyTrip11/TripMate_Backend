package com.ssafy.tripmate.board.service;

import com.ssafy.tripmate.board.dao.BoardDao;
import com.ssafy.tripmate.board.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDao dao;

    public BoardServiceImpl(BoardDao dao) {
        this.dao = dao;
    }


    @Override
    @Transactional
    public List<BoardResponseDto> findAll() {
        try {
            List<Board> boards = dao.searchAll();
            return boards.stream()
                    .map(BoardResponseDto::new)
                    .toList();
        } catch (SQLException e) {
            throw new BoardException("게시물 목록 조회 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public List<BoardResponseDto> findAllByUserId(String category, int userId) {
        try {
            List<Board> boards = dao.searchAllByUserId(category, userId);
            return boards.stream()
                    .map(BoardResponseDto::new)
                    .toList();
        } catch (SQLException e) {
            throw new BoardException("유저별 게시물 목록 조회 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public List<BoardResponseDto> findAllByKeyword(String category, String keyword) {
        try {
            List<Board> boards = dao.searchAllByKeyword(category, keyword);
            return boards.stream()
                    .map(BoardResponseDto::new)
                    .toList();
        } catch (SQLException e) {
            throw new BoardException("키워드별 게시물 목록 조회 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public List<BoardResponseDto> findAllByCategory(String category) {
        try {
            List<Board> boards = dao.searchAllByCategory(category);
            return boards.stream()
                    .map(BoardResponseDto::new)
                    .toList();
        } catch (SQLException e) {
            throw new BoardException("카테고리별 게시물 목록 조회 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public Board findByBoardId(int boardId) {
        try {
            return dao.searchByBoardId(boardId);
        } catch (SQLException e) {
            throw new BoardException("게시물 조회 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public void delete(int boardId) {
        try {
            Board board = dao.searchByBoardId(boardId);
            if (board == null) {
                return;
            }
            dao.remove(boardId);
        } catch (SQLException e) {
            throw new BoardException("게시물 삭제 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public int update(int boardId, BoardUpdateDto boardUpdateDto) {
        try {
            Board board = dao.searchByBoardId(boardId);
            if (board == null) {
                throw new BoardException("존재하지 않는 게시물입니다");
            }
            board.update(boardUpdateDto);
            dao.update(board);
            return boardId;
        } catch (SQLException e) {
            throw new BoardException("게시물 수정 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public int insert(BoardSaveDto boardSaveDto) {
        Board board = boardSaveDto.toEntity();
        try {
            dao.insert(board);
            return board.getBoardId();
        } catch (SQLException e) {
            throw new BoardException("게시물 등록 중 오류 발생");
        }
    }
}
