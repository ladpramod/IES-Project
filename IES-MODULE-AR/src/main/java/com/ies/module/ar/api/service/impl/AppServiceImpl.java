package com.ies.module.ar.api.service.impl;

import com.ies.module.ar.api.bindings.AppForm;
import com.ies.module.ar.api.client.UserFeignClient;
import com.ies.module.ar.api.entities.AppEntity;
import com.ies.module.ar.api.entities.UserAccountForm;
import com.ies.module.ar.api.exception.ApiException;
import com.ies.module.ar.api.repository.AppRepository;
import com.ies.module.ar.api.service.AppService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AppServiceImpl implements AppService {


    @Autowired
    private AppRepository appRepository;

    @Autowired
    private UserFeignClient client;
    @Override
    public boolean createApplication(AppForm app) {
        try {
            WebClient webClient = WebClient.create();

            String stateName = webClient.get().uri("http://localhost:9293/ssn/{ssn}", app.getSsn()).accept().retrieve().bodyToMono(String.class)
                    .block();

            if ("Rhode Island".equals(stateName)) {
                AppEntity entity = new AppEntity();
                BeanUtils.copyProperties(app, entity);
                UserAccountForm user = client.getUserId(app.getUserId());
                String randomCaseNumber = UUID.randomUUID().toString();
                entity.setCaseNo(randomCaseNumber);
                entity.setUserId(user.getUserId());
                entity.setFullName(app.getFullName());
                entity.setMobile(app.getMobile());
                entity.setGender(app.getGender());
                entity.setEmail(app.getEmail());
                entity.setDob(app.getDob());
                entity.setSsn(app.getSsn());

                appRepository.save(entity);
                return true;
            }

        }catch (Exception e){
            throw new ApiException(e.getMessage());
        }
        return false;
    }
    @Override
    public AppForm findCitizen(String caseNo) {
        AppEntity appEntity = appRepository.findById(caseNo).get();
        AppForm appForm = new AppForm();
        BeanUtils.copyProperties(appEntity,appForm);
//        UserAccountForm userAccountForm = client.getUserId(appForm.getUserId());
//        appForm.setAccountFormList(userAccountForm);
        return appForm;
    }

    @Override
    public List<AppForm> veiwCitizen() {
        List<AppEntity> appEntityList = appRepository.findAll();
        List<AppForm> app = new ArrayList<>();

        for (AppEntity entity: appEntityList){
        AppForm appForm = new AppForm();

        BeanUtils.copyProperties(entity,appForm);
        app.add(appForm);
        }
        return app;
    }


}
