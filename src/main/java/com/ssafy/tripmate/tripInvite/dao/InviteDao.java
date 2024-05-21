package com.ssafy.tripmate.tripInvite.dao;

import com.ssafy.tripmate.board.dto.Board;
import com.ssafy.tripmate.tripInvite.dto.Invite;

import java.sql.SQLException;
import java.util.List;

public interface InviteDao {
    public List<Invite> searchAllByTripId(Integer tripId) throws SQLException;

    public Invite searchByInviteId(int inviteId) throws SQLException;

    public void remove(int inviteId) throws SQLException;

    public void update(Invite invite) throws SQLException;

    public int insert(Invite invite) throws SQLException;
}
