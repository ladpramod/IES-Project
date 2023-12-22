package com.ies.module.ar.api.client;

import com.ies.module.ar.api.entities.UserAccountForm;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "IES-MODULE-ADMIN",url = "http://localhost:8184/ies/admin/view")
public interface UserFeignClient {

    @GetMapping("/{userId}")
    public UserAccountForm getUserId(@PathVariable Integer userId);



}
