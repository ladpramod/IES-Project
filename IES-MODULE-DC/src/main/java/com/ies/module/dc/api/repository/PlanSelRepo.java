package com.ies.module.dc.api.repository;

import com.ies.module.dc.api.entity.PlanSelectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PlanSelRepo extends JpaRepository<PlanSelectionEntity, Integer>{

    public PlanSelectionEntity findByCaseNo(String caseNo);

}
