package com.ssafy.tripmate.trip.service;

import com.ssafy.tripmate.trip.dto.Trip;
import com.ssafy.tripmate.trip.dto.TripResponseDto;
import com.ssafy.tripmate.trip.dto.TripSaveDto;
import com.ssafy.tripmate.trip.dto.TripUpdateDto;

import java.util.List;

public interface TripService {
    public List<TripResponseDto> findAll();

    public List<TripResponseDto> findAllByUserId(int userId);

    public Trip findByTripId(int tripId);

    public void delete(int tripId);

    public int update(int tripId, TripUpdateDto tripUpdateDto);

    public int insert(TripSaveDto tripSaveDto);
}
