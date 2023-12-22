package com.ies.admin.service.impl;

import com.ies.admin.bindings.PlanDto;
import com.ies.admin.constants.AppConstant;
import com.ies.admin.entity.PlanEntity;
import com.ies.admin.entity.UserEntity;
import com.ies.admin.exceptions.ApiException;
import com.ies.admin.repository.PlanRepository;
import com.ies.admin.repository.UserRepo;
import com.ies.admin.service.PlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean savePlan(PlanDto plan, Integer userId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new ApiException("user Id '" + userId + "' not found.."));
        PlanEntity planEntity = modelMapper.map(user, PlanEntity.class);
        planEntity.setPlanName(plan.getPlanName());
        planEntity.setCategory(plan.getCategory());
        planEntity.setEndDate(plan.getEndDate());
        planEntity.setUser(plan.getUserId());
        planEntity.setStartDate(plan.getStartDate());
        planRepository.save(planEntity);
        return true;
    }

    @Override
    public List<PlanDto> viewPlans() {
        List<PlanEntity> list = planRepository.findAll();
        List<PlanDto> collected = list.stream().map((plan) -> this.modelMapper.map(plan, PlanDto.class)).collect(Collectors.toList());
        return collected;
    }

    @Override
    public boolean updatePlan(PlanDto plan, Integer planId) {
        PlanEntity planEntity = planRepository.findById(planId).orElseThrow(() -> new ApiException("PlanId '" + planId + "' not found.."));
            planEntity.setPlanName(plan.getPlanName());
            planEntity.setCategory(planEntity.getCategory());
            planEntity.setEndDate(plan.getEndDate());
        PlanEntity saved = planRepository.save(planEntity);
        modelMapper.map(saved,PlanDto.class);
        return true;
    }

    @Override
    public boolean planActive(Integer planId) {
        Optional<PlanEntity> id = planRepository.findById(planId);
        if (id.isPresent()) {
            PlanEntity planEntity = id.get();
            if (planEntity.getActiveSw().contains(AppConstant.ACTIVE_SW)) {
                planEntity.setActiveSw(AppConstant.INACTIVE_SW);
                planRepository.save(planEntity);
            } else if (planEntity.getActiveSw().contains(AppConstant.INACTIVE_SW)) {
                planEntity.setActiveSw(AppConstant.ACTIVE_SW);
                planRepository.save(planEntity);
            }
        }
        return true;
    }


}
