package com.ssafy.tripmate.plan.service;

import com.ssafy.tripmate.plan.dao.PlanDao;
import com.ssafy.tripmate.plan.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PlanServiceImpl implements PlanService {
    private final PlanDao dao;

    public PlanServiceImpl(PlanDao dao) {
        this.dao = dao;
    }

    @Override
    public int create(PlanSaveDto planSaveDto) {
        try {
            log.debug("[PLAN]insert DTO>>>>> {}", planSaveDto);
            Plan plan = planSaveDto.toEntity();
            log.debug("[PLAN]insert>>>>>> {}", plan);
            dao.insert(plan);
            return plan.getPlanId();
        } catch (SQLException e) {
            throw new PlanException(e.getMessage());
        }
    }

    @Override
    public List<PlanResponseDto> searchAll(int tripId) {
        try {
            List<Plan> plans = dao.searchAll(tripId);
            return plans.stream()
                    .map(PlanResponseDto::new)
                    .toList();
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public PlanResponseDto search(int planId) {
        try {
            Plan plan = dao.search(planId);
            return new PlanResponseDto(plan);
        } catch (SQLException e) {
            throw new PlanException(e.getMessage());
        }
    }

    @Override
    public int update(int planId, PlanUpdateDto planUpdateDto) {
        try {
            Plan plan = dao.search(planId);
            if (plan==null) {
                throw new PlanException("존재하지 않는 장소입니다");
            }
            plan.update(planUpdateDto);
            dao.update(plan);
            return planId;
        } catch (SQLException e) {
            throw new PlanException(e.getMessage());
        }
    }

    @Override
    public void remove(int planId) {
        try {
            Plan plan = dao.search(planId);
            if (plan != null) {
                dao.remove(planId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
