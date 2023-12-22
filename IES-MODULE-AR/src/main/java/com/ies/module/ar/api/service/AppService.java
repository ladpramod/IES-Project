package com.ies.module.ar.api.service;

import com.ies.module.ar.api.bindings.AppForm;
import com.ies.module.ar.api.entities.AppEntity;

import java.util.List;

public interface AppService {

    public boolean createApplication(AppForm app);

    public AppForm findCitizen(String caseNo);

    public List<AppForm> veiwCitizen();
}
