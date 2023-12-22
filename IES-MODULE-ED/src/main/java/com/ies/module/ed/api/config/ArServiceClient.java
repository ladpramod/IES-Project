package com.ies.module.ed.api.config;

import com.ies.module.ed.api.bindings.AppForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "IES-MODULE-AR", url = "http://localhost:8185/ies/AR-APP")
public interface ArServiceClient {

    @GetMapping("/{caseNo}")
    public AppForm getApp(@PathVariable String caseNo);
}
