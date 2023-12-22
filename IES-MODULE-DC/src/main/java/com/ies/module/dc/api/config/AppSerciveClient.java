package com.ies.module.dc.api.config;

import com.ies.module.dc.api.bindings.AppForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "IES-MODULE-AR",url = "http://localhost:8185/ies/AR-APP")
public interface AppSerciveClient {

    @GetMapping("/{caseNo}")
    public AppForm citizenGet(@PathVariable String caseNo);
}
