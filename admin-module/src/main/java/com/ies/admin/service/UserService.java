package com.ies.admin.service;

import com.ies.admin.bindings.*;

import java.util.List;

public interface UserService {

    public boolean login(LoginForm form);

    boolean createAccount(UserDto caseWorker);

    List<UserDto> viewAccount();

    boolean updateAccount(UserDto userDto, Integer caseWorkerId);

    boolean accountActiveStatus(UnlockForm form);

    boolean accountLockStatus(LockForm form);

    boolean forgotPwd(ForgotPwdSetter forgotPwdSetter);

    boolean accActiveSw(Integer userId);

    public DashboardForm dashboardData();


}
