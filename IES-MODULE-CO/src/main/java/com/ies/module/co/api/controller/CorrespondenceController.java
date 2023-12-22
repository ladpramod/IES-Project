package com.ies.module.co.api.controller;


import com.ies.module.co.api.bindings.CoForm;
import com.ies.module.co.api.service.CorrespondenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/ies/co")
public class CorrespondenceController {
	
	@Autowired
	private CorrespondenceService corrService;

	@GetMapping("/getCoDetails/{caseNo}")
	public ResponseEntity<String> generateCo(@PathVariable String caseNo)  {
		
		corrService.getEdDetails(caseNo);

		return ResponseEntity.ok("eligibility details stored successfully..");
	}

	@GetMapping("generateCo/{caseNo}")
	public ResponseEntity<String> generateCorrespondence(HttpServletResponse response ,@PathVariable String caseNo) throws Exception{

		response.setContentType("application/pdf");

		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=Citizen.pdf";
		response.addHeader(headerKey,headerValue);
		corrService.generateNotice(response,caseNo);

		return ResponseEntity.ok("Correspondence generated successfully...");
	}

	@GetMapping("/getCoDetails")
	public ResponseEntity<List<CoForm>> viewCoDetails(){
		List<CoForm> coForms = corrService.viewEdDetails();
		return ResponseEntity.ok(coForms);
	}
}
