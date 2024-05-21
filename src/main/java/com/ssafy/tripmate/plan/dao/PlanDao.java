package com.ssafy.tripmate.plan.dao;

import com.ssafy.tripmate.plan.dto.Plan;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlanDao {
    int insert(Plan plan) throws SQLException;

    Plan search(int planId) throws SQLException;

    List<Plan> searchAll(int tripId) throws SQLException;

    void update(Plan plan) throws SQLException;

    void remove(int planId) throws SQLException;
}
