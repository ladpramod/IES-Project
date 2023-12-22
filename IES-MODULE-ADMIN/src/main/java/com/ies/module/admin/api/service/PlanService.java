package com.ies.module.admin.api.service;

import java.util.List;
import java.util.Optional;

import com.ies.module.admin.api.bindings.PlanActiveSwitch;
import com.ies.module.admin.api.bindings.PlanForm;
import com.ies.module.admin.api.entities.PlanEntity;

public interface PlanService {

    public boolean createPlan(PlanForm planForm);

    public List<PlanForm> viewPlans();

    public PlanForm getPlan(String planName);

    public boolean updatePlan(PlanForm planForm, Integer planId);

    public String activePlanAccSw(Integer planId);
}
