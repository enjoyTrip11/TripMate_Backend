package com.ssafy.tripmate.tripInvite.service;

import com.ssafy.tripmate.board.dto.Board;
import com.ssafy.tripmate.board.dto.BoardException;
import com.ssafy.tripmate.board.dto.BoardResponseDto;
import com.ssafy.tripmate.trip.dto.Trip;
import com.ssafy.tripmate.trip.dto.TripException;
import com.ssafy.tripmate.tripInvite.dao.InviteDao;
import com.ssafy.tripmate.tripInvite.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class InviteServiceImpl implements InviteService {

    private final InviteDao dao;

    public InviteServiceImpl(InviteDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<InviteResponseDto> findAllByTripId(int tripId) {
        try {
            List<Invite> invites = dao.searchAllByTripId(tripId);
            return invites.stream()
                    .map(InviteResponseDto::new)
                    .toList();
        } catch (SQLException e) {
            throw new InviteException("여행별 친구 목록 조회 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public Invite searchByInviteId(int inviteId) {
        try {
            return dao.searchByInviteId(inviteId);
        } catch (SQLException e) {
            throw new TripException("친구 조회 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public void delete(int inviteId) {
        try {
            Invite invite = dao.searchByInviteId(inviteId);
            if (invite == null) {
                return;
            }
            dao.remove(inviteId);
        } catch (SQLException e) {
            throw new TripException("친구 삭제 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public int update(int inviteId, InviteUpdateDto inviteUpdateDto) {
        try {
            Invite invite = dao.searchByInviteId(inviteId);
            if (invite == null) {
                throw new TripException("존재하지 않는 친구입니다");
            }
            invite.update(inviteUpdateDto);
            dao.update(invite);
            return inviteId;
        } catch (SQLException e) {
            throw new TripException("친구 수정 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public int insert(InviteSaveDto inviteSaveDto) {
        Invite invite = inviteSaveDto.toEntity();
        try {
            dao.insert(invite);
            return invite.getInviteId();
        } catch (SQLException e) {
            throw new TripException("게시물 등록 중 오류 발생");
        }
    }
}
