package com.geekyants.ads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekyants.ads.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
