package com.ies.module.report.Config;

import com.ies.module.report.Bindings.AppForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "IES-MODULE-AR", url = "http://localhost:8185/ies/AR-APP")
public interface ArFeignClient {

    @GetMapping("/{caseNo}")
    public AppForm getArDetails(@PathVariable String caseNo);


}
