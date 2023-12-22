package com.ies.module.dc.api.service.impl;

import com.ies.module.dc.api.bindings.*;
import com.ies.module.dc.api.config.AppSerciveClient;
import com.ies.module.dc.api.config.PlanServiceClient;
import com.ies.module.dc.api.entity.EducationEntity;
import com.ies.module.dc.api.entity.IncomeEntity;
import com.ies.module.dc.api.entity.KidsEntity;
import com.ies.module.dc.api.entity.PlanSelectionEntity;
import com.ies.module.dc.api.repository.EducationRepo;
import com.ies.module.dc.api.repository.IncomeRepo;
import com.ies.module.dc.api.repository.KidRepo;
import com.ies.module.dc.api.repository.PlanSelRepo;
import com.ies.module.dc.api.service.DcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DcServiceImpl implements DcService {

    @Autowired
    private PlanServiceClient client;
    @Autowired
    private IncomeRepo incomeRepo;
    @Autowired
    private EducationRepo educationRepo;
    @Autowired
    private KidRepo kidRepo;
    @Autowired
    private PlanSelRepo planSelRepo;
    @Autowired
    private AppSerciveClient appSerciveClient;

    @Override
    public List<String> getPlansName() {
        //planRepo.findAll();
        List<PlanForm> plans = client.getAllPlans();
        return plans.stream().map(PlanForm::getPlanName).collect(Collectors.toList());
    }

    @Override
    public String saveSelectedPlan(PlanSelection select) {

        AppForm appForm = appSerciveClient.citizenGet(select.getCaseNo());

        PlanSelectionEntity planSelection = new PlanSelectionEntity();
            BeanUtils.copyProperties(appForm,planSelection);
            PlanForm plan = client.getSinglePlan(select.getPlanName());
            planSelection.setPlanName(plan.getPlanName());
            planSelection.setCaseNo(appForm.getCaseNo());
            planSelection.setUserId(select.getUserId());
        planSelRepo.save(planSelection);
        return "success";
    }

    @Override
    public String saveIncomeData(Income income) {
        IncomeEntity incomeEntity = new IncomeEntity();

        AppForm appForm = appSerciveClient.citizenGet(income.getCaseNo());
        BeanUtils.copyProperties(income, incomeEntity);
            income.setPropertyIncome(income.getPropertyIncome());
            incomeEntity.setRentIncome(income.getRentIncome());
            incomeEntity.setMonthlySalaryIncome(income.getMonthlySalaryIncome());
            incomeEntity.setCaseNo(appForm.getCaseNo());
            incomeEntity.setUserId(income.getUserId());
            incomeRepo.save(incomeEntity);

        return "success";
    }

    @Override
    public String saveEducationData(Education education) {
        EducationEntity eduEntity = new EducationEntity();

        AppForm appForm = appSerciveClient.citizenGet(education.getCaseNo());

        BeanUtils.copyProperties(education, eduEntity);
        eduEntity.setCaseNo(appForm.getCaseNo());
        eduEntity.setUserId(education.getUserId());
        eduEntity.setHighestDegree(education.getHighestDegree());
        eduEntity.setGraduationYear(education.getGraduationYear());
        eduEntity.setUniversityName(education.getUniversityName());
        educationRepo.save(eduEntity);

        return "success";
    }

    @Override
    public String saveKidsData(ChildReq req) {
        List<Child> childs = req.getChild();

        AppForm appForm = appSerciveClient.citizenGet(req.getCaseNo());
            for (Child k : childs) {
                KidsEntity entity = new KidsEntity();
                BeanUtils.copyProperties(k, entity);
                entity.setCaseNo(appForm.getCaseNo());
                entity.setUserId(req.getUserId());
                kidRepo.save(entity);
            }

        return "success";
    }

    @Override
    public DcSummary getSummary(String caseNo) {
        IncomeEntity incomeEntity = incomeRepo.findByCaseNo(caseNo);
        EducationEntity education = educationRepo.findByCaseNo(caseNo);
        List<KidsEntity> kidsEntity = kidRepo.findByCaseNo(caseNo);

        DcSummary summary = new DcSummary();

        Income income = new Income();
        BeanUtils.copyProperties(incomeEntity, income);
        income.setCaseNo(incomeEntity.getCaseNo());
        summary.setIncome(income);

        Education edu = new Education();
        BeanUtils.copyProperties(education, edu);
        edu.setCaseNo(education.getCaseNo());
        summary.setEducation(edu);

        List<Child> childs = new ArrayList<>();

        for (KidsEntity kids : kidsEntity) {
            Child ch = new Child();
            BeanUtils.copyProperties(kids, ch);
            childs.add(ch);
        }
        summary.setChilds(childs);
        return summary;
    }

    @Override
    public PlanSelection getPlan(String caseNo) {

        PlanSelectionEntity planSelectionEntity = planSelRepo.findByCaseNo(caseNo);
        PlanSelection planSel = new PlanSelection();
        BeanUtils.copyProperties(planSelectionEntity,planSel);
//        AppForm appForm = appSerciveClient.citizenGet(caseNo);
//        planSel.setUserAccountForm(appForm.getAccountFormList());

        return planSel;
    }

    @Override
    public Income getIncome(String caseNo) {
        IncomeEntity income = incomeRepo.findByCaseNo(caseNo);
        Income incomeData = new Income();
        BeanUtils.copyProperties(income,incomeData);
        return incomeData;
    }

    @Override
    public Education getEducation(String caseNo) {
        EducationEntity education = educationRepo.findByCaseNo(caseNo);
        Education educationData = new Education();
        BeanUtils.copyProperties(education,educationData);
        return educationData;
    }

    @Override
    public ChildReq getChild(String caseNo) {
        List<KidsEntity> kidsEntities = kidRepo.findByCaseNo(caseNo);

        List<Child> child = new ArrayList<>();
        ChildReq childReq = new ChildReq();

        for (KidsEntity kids:kidsEntities) {
            Child ch = new Child();
            BeanUtils.copyProperties(kids,ch);
            child.add(ch);
            childReq.setCaseNo(kids.getCaseNo());
            childReq.setUserId(kids.getUserId());
        }
        childReq.setChild(child);
        return childReq;
    }
}
