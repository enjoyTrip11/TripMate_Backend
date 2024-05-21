package com.ssafy.tripmate.place.dao;

import com.ssafy.tripmate.place.dto.HotPlace;
import com.ssafy.tripmate.place.dto.HotPlaceResponseDto;
import com.ssafy.tripmate.place.dto.Place;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface HotPlaceDao {

    HotPlace searchByBoardUser(int boardId, int userId) throws SQLException;

    HotPlace searchById(int hotplaceId) throws SQLException;

    void insert(int boardId, int userId) throws SQLException;

    void delete(int hotplaceId) throws SQLException;

    int countHotPlaceHits(int placeId) throws SQLException;

    List<Place> searchHotPlaceByUser(int userId) throws SQLException;

    List<HotPlaceResponseDto> searchAllHotPlace() throws SQLException;
}
