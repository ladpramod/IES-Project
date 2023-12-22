package com.ies.module.report.Config;


import com.ies.module.report.Bindings.EdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "IES-MODULE-ED", url = "http://localhost:8187/ies/ED/getCitizenData")
public interface EdFeignClient {

    @GetMapping("/{caseNo}")
    public EdResponse getEdDetails(@PathVariable String caseNo);
}
