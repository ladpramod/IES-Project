package com.ies.module.ed.api.service;


import com.ies.module.ed.api.bindings.*;
import com.ies.module.ed.api.config.ArServiceClient;
import com.ies.module.ed.api.config.DcServiceClient;
import com.ies.module.ed.api.entities.EligEntity;
import com.ies.module.ed.api.repositories.EligRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EligServiceImpl implements EligService {

	@Autowired
	private DcServiceClient dcServiceClient;
	@Autowired
	private EligRepository eligRepository;

	@Autowired private ArServiceClient arServiceClient;
	@Override
	public EdResponse determineEligibility(String caseNum) {

		EdResponse response = new EdResponse();


		PlanSelection planSelection = dcServiceClient.getplan(caseNum);
		String planName = planSelection.getPlanName();

		AppForm arForm = arServiceClient.getApp(caseNum);

		Income income = dcServiceClient.getIncome(caseNum);
		Education education = dcServiceClient.getEducation(caseNum);
		ChildReq kids = dcServiceClient.getKids(caseNum);

		response.setPlanName(planName);
		response.setCaseNo(planSelection.getCaseNo());

		Double monthlySalaryIncome = income.getMonthlySalaryIncome();
		Double rentIncome = income.getRentIncome();
		Double propertyIncome = income.getPropertyIncome();


		double totalIncome = monthlySalaryIncome + rentIncome + propertyIncome;

		if ("SNAP".equals(planName)) {

			if (monthlySalaryIncome > 300) {
				response.setPlanStatus("DENIED");
				response.setDenialRsn("High Salary Income");
				response.setEligStartDate(LocalDate.now());
				response.setEligEndDate(LocalDate.now().plusMonths(6));

			} else {
				response.setPlanStatus("APPROVED");
				response.setBenefitAmt(350.00);
				response.setEligStartDate(LocalDate.now());
				response.setEligEndDate(LocalDate.now().plusMonths(6));
			}

		} else if ("CCAP".equals(planName)) {
			int count = 0;
			List<Child> child = kids.getChild();
			for (Child kid : child) {
				if (kid.getKidAge() > 16) {
					count++;
				}
			}
			if (monthlySalaryIncome > 300 && count!=0) {
				response.setPlanStatus("DENIED");
				response.setDenialRsn("High Salary Income & Kids Age Above 16");
				response.setEligStartDate(LocalDate.now());
				response.setEligEndDate(LocalDate.now().plusMonths(6));
			} else {
				response.setPlanStatus("APPROVED");
				response.setBenefitAmt(350.00);
				response.setEligStartDate(LocalDate.now());
				response.setEligEndDate(LocalDate.now().plusMonths(6));
			}

		} else if ("Medicade".equals(planName)) {
			if (totalIncome > 300) {
				response.setPlanStatus("DENIED");
				response.setDenialRsn("Income resource is higher than plan conditions..");
				response.setEligStartDate(LocalDate.now());
				response.setEligEndDate(LocalDate.now().plusMonths(6));
			} else {
				response.setPlanStatus("APPROVED");
				response.setBenefitAmt(450.00);
				response.setEligStartDate(LocalDate.now());
				response.setEligEndDate(LocalDate.now().plusMonths(6));
			}
		} else if ("Medicare".equals(planName)) {
			String dob = arForm.getDob();
			LocalDate parse = LocalDate.parse(dob);
			int years = Period.between(parse, LocalDate.now()).getYears();
			if (years < 65){
				response.setPlanStatus("DENIED");
				response.setDenialRsn("Age criteria failed..");
				response.setEligStartDate(LocalDate.now());
				response.setEligEndDate(LocalDate.now().plusMonths(6));
			}else {
				response.setPlanStatus("APPROVED");
				response.setBenefitAmt(250.00);
				response.setEligStartDate(LocalDate.now());
				response.setEligEndDate(LocalDate.now().plusMonths(6));
			}

		} else if ("RIW".equals(planName)) {
			if (monthlySalaryIncome > 0 && ("Post-Graduate".equals(education.getHighestDegree())
					|| ("Graduate".equals(education.getHighestDegree())))){
				response.setPlanStatus("DENIED");
				response.setDenialRsn("Salary conditions failed..");
				response.setEligStartDate(LocalDate.now());
				response.setEligEndDate(LocalDate.now().plusMonths(6));
			}else {
				response.setPlanStatus("APPROVED");
				response.setBenefitAmt(250.00);
				response.setEligStartDate(LocalDate.now());
				response.setEligEndDate(LocalDate.now().plusMonths(6));
			}
		}

		EligEntity entity = new EligEntity();

			String randomId = UUID.randomUUID().toString();
			entity.setEdgTraceId(randomId);
			BeanUtils.copyProperties(response, entity);
			eligRepository.save(entity);
			return response;
	}

	@Override
	public List<EdResponse> getCitizenList() {

		// insert into co table

  		List<EligEntity> eligCitizenList = eligRepository.findAll();
		List<EdResponse> list = new ArrayList<>();


		for (EligEntity entity : eligCitizenList) {
			EdResponse entry = new EdResponse();
			BeanUtils.copyProperties(entity, entry);
			list.add(entry);
		}

		return list;
	}

	@Override
	public EdResponse getCitizenEd(String caseNum) {
		EligEntity eligEntity = eligRepository.findByCaseNo(caseNum);
		EdResponse edResponse = new EdResponse();
		BeanUtils.copyProperties(eligEntity,edResponse);
		return edResponse;
	}

}
