package com.ies.module.admin.api.service.impl;

import com.ies.module.admin.api.bindings.DashboardCards;
import com.ies.module.admin.api.bindings.LoginForm;
import com.ies.module.admin.api.bindings.UnlockAccForm;
import com.ies.module.admin.api.bindings.UserAccountForm;
import com.ies.module.admin.api.constants.AppConstants;
import com.ies.module.admin.api.entities.EligEntity;
import com.ies.module.admin.api.entities.User;
import com.ies.module.admin.api.exception.ApiException;
import org.springframework.security.core.AuthenticationException;
import com.ies.module.admin.api.repository.EligRepository;
import com.ies.module.admin.api.repository.PlanRepository;
import com.ies.module.admin.api.repository.UserRepository;
import com.ies.module.admin.api.service.UserService;
import com.ies.module.admin.api.utilities.EmailUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private EligRepository eligRepository;

    @Autowired
    private EmailUtils emailUtils;
    @Autowired private AuthenticationManager authManager;

    @Autowired private PasswordEncoder passwordEncoder;
    @Override
    public String login(LoginForm loginForm) {

        User entity = userRepository.findByEmailAndPazzd(loginForm.getUsername(), loginForm.getPassword());
        if (entity==null){
            return "Invalid Credentials..";
        } else if (AppConstants.ACCSWITCHACTIVE.equals(entity.getActiveSw()) &&
                AppConstants.ACCSTATUSUNLOCKED.equals(entity.getAccStatus())) {
            return "Login Success...";
        }
        else{
            return "Account LOCKED...";
        }

    }

    @Override
    public boolean recoverPwd(LoginForm loginForm) {
        Optional<User> username = userRepository.findByEmail(loginForm.getUsername());
        if (username.isPresent()){
            User user = username.get();
            if(user.getEmail().equals(loginForm.getUsername())){
                String sub = "Recover password";
                String body = "Your password is: " +user.getPazzd();
                emailUtils.emailSend(user.getEmail(),sub,body);
            }
            return true;
        }
 //       else if (user.getEmail().equals(loginForm.getEmail())) {
//            String sub = "Recover password";
//            String body = "Your password is: " +entity.getPazzd();
//            emailUtils.emailSend(entity.getEmail(),sub,body);
//            return true;
//        }
        else {
            return false;
        }

    }

    @Override
    public DashboardCards fetchDashboardInfo() {

        long plansCount = planRepository.count();

        List<EligEntity> eligEntityList = eligRepository.findAll();
        long approvedCnt = eligEntityList.stream().filter(ed -> ed.getPlanStatus().equals("APPROVED")).count();
        long deniedCnt = eligEntityList.stream().filter(ed -> ed.getPlanStatus().equals("DENIED")).count();
        double totalBenefitAmtIssue = eligEntityList.stream().mapToDouble(ed -> ed.getBenefitAmt()).sum();

        DashboardCards dashboardCards = new DashboardCards();
        dashboardCards.setPlansCnt(plansCount);
        dashboardCards.setApprovedCnt(approvedCnt);
        dashboardCards.setDeniedCnt(deniedCnt);
        dashboardCards.setBenefitAmtGiven(totalBenefitAmtIssue);
        return dashboardCards;
        
    }

    @Override
    public Boolean unlockUserAccount(UnlockAccForm accForm) {

        Optional<User> email = userRepository.findByEmail(accForm.getEmail());
        if (email.isPresent()){
            User entity = email.get();

            if (entity.getEmail().equals(accForm.getEmail()) && accForm.getTemPazzwd().equals(entity.getPazzd()))

                entity.setPazzd(passwordEncoder.encode(accForm.getNewPazz()));
                entity.setActiveSw(AppConstants.ACCSWITCHACTIVE);
                entity.setAccStatus(AppConstants.ACCSTATUSUNLOCKED);
                userRepository.save(entity);

                String to = entity.getEmail();
                String sub = "Account Activation status.";
                String body = "Your account is successfully activated. \n \n Your new password is: " + entity.getPazzd();
                emailUtils.emailSend(to, sub, body);

                return true;

        }else {

            throw new ApiException("Invalid Credentials..");
        }
    }

    @Override
    public UserAccountForm findByUserEmail(String email) {
        User user = userRepository.findByEmail(email).get();
            UserAccountForm userForm = new UserAccountForm();
            BeanUtils.copyProperties(user, userForm);
            return userForm;
    }
}
