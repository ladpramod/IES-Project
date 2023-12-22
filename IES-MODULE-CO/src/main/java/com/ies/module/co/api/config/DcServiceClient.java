package com.ies.module.co.api.config;

import com.ies.module.co.api.bindings.PlanSelection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "IES-MODULE-DC", url = "http://localhost:8186/ies/DC/getSinglePlan")
public interface DcServiceClient {

    @GetMapping("/{caseNo}")
    public PlanSelection findDCUser(@PathVariable String caseNo);
}
