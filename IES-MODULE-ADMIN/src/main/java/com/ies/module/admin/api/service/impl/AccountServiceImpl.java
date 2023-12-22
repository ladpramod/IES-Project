package com.ies.module.admin.api.service.impl;

import com.ies.module.admin.api.bindings.UserAccountForm;
import com.ies.module.admin.api.constants.AppConstants;
import com.ies.module.admin.api.entities.Role;
import com.ies.module.admin.api.entities.User;
import com.ies.module.admin.api.exception.ApiException;
import com.ies.module.admin.api.repository.RoleRepository;
import com.ies.module.admin.api.repository.UserRepository;
import com.ies.module.admin.api.service.AccountService;
import com.ies.module.admin.api.utilities.EmailUtils;
import com.ies.module.admin.api.utilities.PazzwdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

public class AccountServiceImpl implements AccountService {


	Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired private EmailUtils emailUtils;
	@Autowired private UserRepository userRepository;
	@Autowired private PazzwdGenerator pazzwdGenerator;
	@Autowired private PasswordEncoder passwordEncoder;

	@Autowired private RoleRepository roleRepository;


	@Override
	public Boolean createUserAccount(UserAccountForm accountForm) {

		User entity = new User();
		BeanUtils.copyProperties(accountForm, entity);
		String pazzedGenerator = pazzwdGenerator.pazzedGenerator();
//		entity.setPazzd(pazzedGenerator);
		String paswrd = passwordEncoder.encode(pazzedGenerator);
		entity.setPazzd(paswrd);
		entity.setActiveSw(AppConstants.ACCSWITCHINACTIVE);
		entity.setAccStatus(AppConstants.ACCSTATUSLOCKED);
		Role role = roleRepository.findByRoleName(accountForm.getRoleName());
		entity.getRoles().add(role);
		userRepository.save(entity);


		String to = entity.getEmail();
		String sub = "Account created.";
		String body = "Your Account created successfully.\n\n Your Temp Password is: '"+ entity.getPazzd()+"'" +
				"\n\n your role: '"+entity.getRoles()+"'" ;

		emailUtils.emailSend(to, sub, body);
		return true;

	}

	@Override
	public List<UserAccountForm> viewUserAccount() {


		List<User> entityList = userRepository.findAll();

		List<UserAccountForm> userAccountForms = new ArrayList<>();

		for (User userEntity : entityList) {
			UserAccountForm user = new UserAccountForm();
			BeanUtils.copyProperties(userEntity,user);
			userAccountForms.add(user);
		}
		return userAccountForms;
	}

	@Override
	public UserAccountForm getUserAccById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			User entity = user.get();
			UserAccountForm accountForm = new UserAccountForm();
			BeanUtils.copyProperties(entity, accountForm);
			return accountForm;
		}
		return null;
	}

	@Override
	public String activeSwStatus(Integer userId) {
		User entity = userRepository.findById(userId).orElseThrow(()
				-> new ApiException("userId '"+userId+"' not found"));

		logger.debug("Checking current switch status process started..");

		if (entity.getActiveSw().contains(AppConstants.ACCSWITCHINACTIVE)) {
			logger.info("Changing current switch status and update process started..");

			entity.setActiveSw(AppConstants.ACCSWITCHACTIVE);
			logger.info("status updated and saved into database..");

			userRepository.save(entity);

		} else if (entity.getActiveSw().contains(AppConstants.ACCSWITCHACTIVE)) {

			logger.info("Changing current switch status and update process started..");
				entity.setActiveSw(AppConstants.ACCSWITCHINACTIVE);
				
			logger.info("status updated and saved into database..");
				userRepository.save(entity);
			}

		return "Account status changed..";
	}

}
