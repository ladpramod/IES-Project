package com.ies.admin.service;

import com.ies.admin.entity.PlanEntity;
import com.ies.admin.bindings.PlanDto;

import java.util.List;

public interface PlanService {

    boolean savePlan(PlanDto plan, Integer caseWorkerId);

    List<PlanDto> viewPlans();

    boolean updatePlan(PlanDto plan, Integer planId);

    boolean planActive(Integer planId);


}
