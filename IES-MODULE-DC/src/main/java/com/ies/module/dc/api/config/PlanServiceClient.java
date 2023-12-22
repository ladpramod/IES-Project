package com.ies.module.dc.api.config;

import com.ies.module.dc.api.bindings.PlanForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "IES-MODULE-ADMIN", url = "http://localhost:8184/ies/admin/plans")


public interface PlanServiceClient {

@GetMapping("/viewPlans")
    public List<PlanForm> getAllPlans();

@GetMapping("/getplan/{planName}")
    public PlanForm getSinglePlan(@PathVariable String planName);


}


