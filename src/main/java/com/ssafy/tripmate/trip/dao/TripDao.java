package com.ssafy.tripmate.trip.dao;

import com.ssafy.tripmate.trip.dto.Trip;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TripDao {
    public List<Trip> searchAll() throws SQLException;

    public List<Trip> searchAllByUserId(int userId) throws SQLException;

    public Trip searchByTripId(int tripId) throws SQLException;

    public void remove(int tripId) throws SQLException;

    public void update(Trip trip) throws SQLException;

    public int insert(Trip trip) throws SQLException;
}
