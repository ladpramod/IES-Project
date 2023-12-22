package com.ies.module.admin.api.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ies.module.admin.api.exception.ApiException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ies.module.admin.api.bindings.PlanActiveSwitch;
import com.ies.module.admin.api.bindings.PlanForm;
import com.ies.module.admin.api.constants.AppConstants;
import com.ies.module.admin.api.entities.PlanEntity;
import com.ies.module.admin.api.entities.User;
import com.ies.module.admin.api.repository.PlanRepository;
import com.ies.module.admin.api.repository.UserRepository;
import com.ies.module.admin.api.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

	private Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);
	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean createPlan(PlanForm planForm) {

		PlanEntity planEntity = this.mapper.map(planForm, PlanEntity.class);
		User user = this.userRepository.findById(planForm.getUserId()).orElseThrow(() -> new ApiException("User Id not found.."));
//		User entity = user.get();
//			PlanEntity planEntity = new PlanEntity();
//			BeanUtils.copyProperties(planForm, planEntity);
			planEntity.setUser(user);
			planEntity.setAccActiveSw(AppConstants.ACCSWITCHINACTIVE);
			planEntity.setCategory(planForm.getCategory());
			planEntity.setPlanName(planForm.getPlanName());
			planEntity.setPlanStartDate(planForm.getPlanStartDate());
			planEntity.setPlanEndDate(planForm.getPlanEndDate());
			PlanEntity entity = planRepository.save(planEntity);
			mapper.map(entity, PlanForm.class);

			return true;
		}

	@Override
	public List<PlanForm> viewPlans() {

		List<PlanEntity> list = planRepository.findAll();
		List<PlanForm> planFormList = list.stream().map((plan) -> this.mapper.map(plan, PlanForm.class)).collect(Collectors.toList());

		return planFormList;
	}

	@Override
	public PlanForm getPlan(String planName) {
		Optional<PlanEntity> id = planRepository.findByPlanName(planName);
		if (id.isPresent()){
			PlanEntity planEntity = id.get();
			PlanForm planForm = new PlanForm();
			BeanUtils.copyProperties(planEntity,planForm);
			return planForm;
		}else {
			throw new ApiException("Plan '"+planName+"' not available..");
		}
	}

	@Override
	public boolean updatePlan(PlanForm planForm, Integer planId) {

		Optional<PlanEntity> id = planRepository.findById(planId);
		if (id.isPresent()) {
			PlanEntity planEntity = id.get();
			BeanUtils.copyProperties(planEntity, planForm);
			planEntity.setCategory(planForm.getCategory());
			planEntity.setPlanName(planForm.getPlanName());
			planEntity.setPlanStartDate(planForm.getPlanStartDate());
			planEntity.setPlanEndDate(planForm.getPlanEndDate());
			planRepository.save(planEntity);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String activePlanAccSw(Integer planId) {

		PlanEntity planEntity = planRepository.findById(planId).orElseThrow(()-> new ApiException("Plan Id not found.."));
		logger.debug("Checking current switch status process started..");
		if (planEntity.getAccActiveSw().contains(AppConstants.ACCSWITCHINACTIVE)) {
			logger.info("Changing current switch status and update process started..");
			planEntity.setAccActiveSw(AppConstants.ACCSWITCHACTIVE);
			logger.info("status updated and saved into database..");
			planRepository.save(planEntity);

		} else if (planEntity.getAccActiveSw().contains(AppConstants.ACCSWITCHACTIVE)) {
			logger.info("Changing current switch status and update process started..");
			planEntity.setAccActiveSw(AppConstants.ACCSWITCHINACTIVE);
			logger.info("status updated and saved into database..");
			planRepository.save(planEntity);
		}
		return "success";
	}
}
