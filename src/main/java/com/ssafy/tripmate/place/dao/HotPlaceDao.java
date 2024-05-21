package com.ssafy.tripmate.place.dao;

import com.ssafy.tripmate.place.dto.Place;

import java.sql.SQLException;
import java.util.List;

public interface HotPlaceDao {

    int insert(int boardId, int userId) throws SQLException;

    void delete(int hotplaceId) throws SQLException;

    int countHotPlaceHits(int placeId) throws SQLException;

    List<Place> searchHotPlaceByUser(int userId) throws SQLException;

    List<Place> searchAllHotPlace() throws SQLException;
}
