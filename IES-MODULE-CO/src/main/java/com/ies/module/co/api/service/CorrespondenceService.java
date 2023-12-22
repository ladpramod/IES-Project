package com.ies.module.co.api.service;

import javax.servlet.http.HttpServletResponse;

import com.ies.module.co.api.bindings.CoForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CorrespondenceService {
	
	public boolean generateNotice(HttpServletResponse response,String caseNo) throws Exception;
	
	public boolean getEdDetails(String caseNo);

	public List<CoForm> viewEdDetails();


}
