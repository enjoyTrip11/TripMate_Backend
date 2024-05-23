package com.ssafy.tripmate.place.dao;

import com.ssafy.tripmate.place.dto.Place;
import com.ssafy.tripmate.place.dto.PlaceResponseDto;
import com.ssafy.tripmate.place.dto.SearchFilter;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlaceDao {
    List<PlaceResponseDto> searchAll(SearchFilter filter) throws SQLException;
}
