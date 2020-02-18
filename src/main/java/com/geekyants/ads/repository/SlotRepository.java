package com.geekyants.ads.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekyants.ads.entity.Slot;
@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
	
	
	List<Slot> findByPlanIdAndStatus(Long planId, String status);


}
