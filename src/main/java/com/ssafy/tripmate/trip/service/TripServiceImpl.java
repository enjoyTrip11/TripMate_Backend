package com.ssafy.tripmate.trip.service;

import com.ssafy.tripmate.trip.dao.TripDao;
import com.ssafy.tripmate.trip.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class TripServiceImpl implements TripService {
    private final TripDao dao;

    public TripServiceImpl(TripDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<TripResponseDto> findAll() {
        try {
            List<Trip> trips = dao.searchAll();
            return trips.stream()
                    .map(TripResponseDto::new)
                    .toList();
        } catch (SQLException e) {
            throw new TripException("여행 목록 조회 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public List<TripResponseDto> findAllByUserId(int userId) {
        try {
            List<Trip> trips = dao.searchAllByUserId(userId);
            return trips.stream()
                    .map(TripResponseDto::new)
                    .toList();
        } catch (SQLException e) {
            throw new TripException("유저별 여행 목록 조회 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public Trip findByTripId(int tripId) {
        try {
            return dao.searchByTripId(tripId);
        } catch (SQLException e) {
            throw new TripException("여행 조회 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public void delete(int tripId) {
        try {
            Trip trip = dao.searchByTripId(tripId);
            if (trip == null) {
                return;
            }
            dao.remove(tripId);
        } catch (SQLException e) {
            throw new TripException("여행 삭제 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public int update(int tripId, TripUpdateDto tripUpdateDto) {
        try {
            Trip trip = dao.searchByTripId(tripId);
            if (trip == null) {
                throw new TripException("존재하지 않는 게시물입니다");
            }
            trip.update(tripUpdateDto);
            dao.update(trip);
            return tripId;
        } catch (SQLException e) {
            throw new TripException("여행 수정 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public int insert(TripSaveDto tripSaveDto) {
        Trip trip = tripSaveDto.toEntity();
        try {
            dao.insert(trip);
            return trip.getTripId();
        } catch (SQLException e) {
            throw new TripException("여행 등록 중 오류 발생");
        }
    }
}
