package com.ies.module.dc.api.service;

import com.ies.module.dc.api.bindings.*;

import java.util.List;

public interface DcService {

    public List<String> getPlansName();

    public String saveSelectedPlan(PlanSelection select);

    public String saveIncomeData(Income income);

    public String saveEducationData(Education education);

    public String saveKidsData(ChildReq req);

    public DcSummary getSummary(String caseNo);

    public PlanSelection getPlan(String caseNo);
    public Income getIncome(String caseNo);
    public Education getEducation(String caseNo);
    public ChildReq getChild(String caseNo);
}
