package com.ssafy.tripmate.place.dao;

import com.ssafy.tripmate.place.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface HotPlaceDao {

    HotPlace searchByLocUser(int locationId, int userId) throws SQLException;

    HotPlace searchById(int hotplaceId) throws SQLException;

    void insert(int locationId, int userId) throws SQLException;

    void delete(int hotplaceId) throws SQLException;

    int countHotPlaceHits(int placeId) throws SQLException;

    List<Place> searchHotPlaceByUser(int userId) throws SQLException;

    List<Map<String, String>> searchAllHotPlace(int userId) throws SQLException;
}
