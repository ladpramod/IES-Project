package com.ies.module.ed.api.service;


import com.ies.module.ed.api.bindings.EdResponse;

import java.util.List;

public interface EligService {

	public EdResponse determineEligibility(String caseNum);

	public List<EdResponse> getCitizenList();

	public EdResponse getCitizenEd(String caseNum);
}
