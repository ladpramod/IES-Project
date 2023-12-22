package com.ies.module.admin.api.service;

import com.ies.module.admin.api.bindings.DashboardCards;
import com.ies.module.admin.api.bindings.LoginForm;
import com.ies.module.admin.api.bindings.UnlockAccForm;
import com.ies.module.admin.api.bindings.UserAccountForm;

public interface UserService {

    public  String login(LoginForm loginForm);

    public boolean recoverPwd(LoginForm loginForm);

    public DashboardCards fetchDashboardInfo();

    public Boolean unlockUserAccount(UnlockAccForm accForm);

    public UserAccountForm findByUserEmail(String email);



}
