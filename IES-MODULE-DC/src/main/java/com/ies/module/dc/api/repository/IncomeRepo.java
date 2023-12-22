package com.ies.module.dc.api.repository;


import com.ies.module.dc.api.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepo extends JpaRepository<IncomeEntity, Integer>{

    public IncomeEntity findByCaseNo(String caseNo);
//    public PlanSelectionEntity findByUserId(Integer userId);
}
