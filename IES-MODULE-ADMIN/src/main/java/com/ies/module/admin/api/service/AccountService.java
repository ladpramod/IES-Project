package com.ies.module.admin.api.service;

import com.ies.module.admin.api.bindings.ActiveSwForm;
import com.ies.module.admin.api.bindings.UnlockAccForm;
import com.ies.module.admin.api.bindings.UserAccountForm;

import java.util.List;

public interface AccountService {
	
	public Boolean createUserAccount(UserAccountForm accountForm);
	
	public List<UserAccountForm> viewUserAccount();
	
	public UserAccountForm getUserAccById(Integer id);
	
	public String activeSwStatus(Integer userId);
	
	

}
