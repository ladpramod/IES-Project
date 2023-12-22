package com.ies.module.co.api.service.impl;

import com.ies.module.co.api.bindings.CoForm;
import com.ies.module.co.api.bindings.EdResponse;
import com.ies.module.co.api.bindings.PlanSelection;
import com.ies.module.co.api.config.DcServiceClient;
import com.ies.module.co.api.config.EdFeignClient;
import com.ies.module.co.api.entity.CoNoticeEntity;
import com.ies.module.co.api.repository.CoNoticeRepository;
import com.ies.module.co.api.service.CorrespondenceService;
import com.ies.module.co.api.utils.EmailUtils;
import com.ies.module.co.api.utils.PdfUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CorrespodenceServiceImpl implements CorrespondenceService {

	@Autowired
	private CoNoticeRepository coNoticeRepository;

	@Autowired
	private PdfUtils pdfUtils;

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private EdFeignClient edFeignClient;

	@Autowired
	private DcServiceClient dcServiceClient;

	@Override
	public boolean generateNotice(HttpServletResponse response,String caseNo) throws Exception {

		CoNoticeEntity coNotice = coNoticeRepository.findByCaseNo(caseNo);
		File f = new File("citizen.pdf");
		pdfUtils.generate(response, coNotice, f);

		String subject = "New Correpondence from IES";
		String body = "Please find the attached new correspondance.";
		String to = "jainpramodlad@gmail.com";

		emailUtils.emailSend(to,subject,body,f);
		f.delete();
		return true;
	}

	@Override
	public boolean getEdDetails(String caseNo){

		EdResponse edDetails = edFeignClient.getEdDetails(caseNo);
		PlanSelection user = dcServiceClient.findDCUser(caseNo);
		String randomId = UUID.randomUUID().toString();


		CoNoticeEntity entity = new CoNoticeEntity();
		entity.setPlanName(edDetails.getPlanName());
		entity.setPlanStatus(edDetails.getPlanStatus());
		entity.setBenefitAmount(edDetails.getBenefitAmt());
		entity.setStartDate(edDetails.getEligStartDate());
		entity.setEndDate(edDetails.getEligEndDate());
		entity.setDenielReason(edDetails.getDenialRsn());
		entity.setUser(user.getUserId());
		entity.setCaseNo(caseNo);
		entity.setNoticeId(randomId);

		coNoticeRepository.save(entity);

		return true;
	}

	@Override
	public List<CoForm> viewEdDetails() {

		List<CoNoticeEntity> coNoticeEntityList = coNoticeRepository.findAll();

		List<CoForm> coFormsList = new ArrayList<>();

		for (CoNoticeEntity entity: coNoticeEntityList){
			CoForm coForm = new CoForm();
			BeanUtils.copyProperties(entity,coForm);
			coFormsList.add(coForm);
		}
		return coFormsList;
	}


}
