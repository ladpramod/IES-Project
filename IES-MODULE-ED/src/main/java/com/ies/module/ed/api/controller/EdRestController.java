package com.ies.module.ed.api.controller;

import com.ies.module.ed.api.bindings.EdResponse;
import com.ies.module.ed.api.service.EligService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ies/ED")
public class EdRestController {

	@Autowired
	private EligService eligService;

	@GetMapping("/check-eligibility/{caseNo}")
	public ResponseEntity<EdResponse> determineElig(@PathVariable String caseNo) {
		EdResponse edResponse = eligService.determineEligibility(caseNo);
		return new ResponseEntity<>(edResponse,HttpStatus.OK);
	}

	@GetMapping("/get-citizens")
	public ResponseEntity<List<EdResponse>> generateCo() {
		List<EdResponse> edResponses = eligService.getCitizenList();
		return new ResponseEntity<>(edResponses, HttpStatus.OK);
	}

	@GetMapping("/getCitizenData/{caseNo}")
	public ResponseEntity<EdResponse> getCitizenElig(@PathVariable String caseNo){
		EdResponse citizenEd = eligService.getCitizenEd(caseNo);
		return ResponseEntity.status(HttpStatus.OK).body(citizenEd);
	}

}
