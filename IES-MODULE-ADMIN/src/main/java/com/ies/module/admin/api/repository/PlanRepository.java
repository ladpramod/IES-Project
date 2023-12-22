package com.ies.module.admin.api.repository;

import com.ies.module.admin.api.bindings.PlanForm;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.module.admin.api.entities.PlanEntity;

import java.util.Optional;


public interface PlanRepository extends JpaRepository<PlanEntity, Integer>{
	
    public Optional<PlanEntity> findByPlanName(String planName);
}
