package com.ies.module.ed.api.config;

import com.ies.module.ed.api.bindings.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "IES-MODULE-DC", url = "http://localhost:8186/ies/DC")
public interface DcServiceClient {

    @GetMapping("/getAllData/{caseNo}")
    public DcSummary getCitizenData(@PathVariable String caseNo);

    @GetMapping("/getSinglePlan/{caseNo}")
    public PlanSelection getplan(@PathVariable String caseNo);

    @GetMapping("/getIncome/{caseNo}")
    public Income getIncome(@PathVariable String caseNo);

    @GetMapping("/getEducation/{caseNo}")
    public Education getEducation(@PathVariable String caseNo);

    @GetMapping("/getKids/{caseNo}")
    public ChildReq getKids(@PathVariable String caseNo);


}
