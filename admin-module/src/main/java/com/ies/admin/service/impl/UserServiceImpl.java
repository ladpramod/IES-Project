package com.ies.admin.service.impl;

import com.ies.admin.constants.AppConstant;
import com.ies.admin.entity.EligEntity;
import com.ies.admin.entity.UserEntity;
import com.ies.admin.exceptions.ApiException;
import com.ies.admin.bindings.*;
import com.ies.admin.repository.EligRepository;
import com.ies.admin.repository.PlanRepository;
import com.ies.admin.repository.UserRepo;
import com.ies.admin.service.UserService;
import com.ies.admin.utils.EmailUtils;
import com.ies.admin.utils.PzdUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private EligRepository eligRepository;

    @Override
    public boolean login(LoginForm form) {
        UserEntity user = userRepo.findByEmail(form.getEmail());
        if (user.getEmail().equalsIgnoreCase(form.getEmail())) {
            if (user.getAccStatus().equalsIgnoreCase(AppConstant.ACC_STATUS_LOCKED)) {
                return false;
            } else if (user.getPazzd().equals(form.getPazzwd())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean createAccount(UserDto entity) {

        UserEntity userEntity = mapper.map(entity, UserEntity.class);

        String randomPwdGenerator = PzdUtils.passwordGenerator(6);

        userEntity.setPazzd(randomPwdGenerator);

        userRepo.save(userEntity);

        //Email implementation after user registered
        String to = userEntity.getEmail();
        String subject = "Login your Account";
        StringBuilder body = new StringBuilder("");
        body.append("Use below  password to login your account \n\n");

        body.append("Password: " + userEntity.getPazzd());

        emailUtils.emailSender(to, subject, body.toString());

        mapper.map(userEntity, UserDto.class);
        return true;
    }

    @Override
    public List<UserDto> viewAccount() {
        List<UserEntity> all = userRepo.findAll();
        List<UserDto> collected = all.stream().map((user) -> this.mapper.map(user, UserDto.class)).collect(Collectors.toList());
        return collected;
    }

    @Override
    public boolean updateAccount(UserDto userDto, Integer userId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new ApiException("userId '" + userId + "' not found in records.."));
        user.setMobileNo(userDto.getMobile());
        user.setDob(userDto.getDob());
        UserEntity userEntity = userRepo.save(user);
        mapper.map(userEntity, UserDto.class);


        return true;
    }

    @Override
    public boolean accountActiveStatus(UnlockForm form) {
        UserEntity user = userRepo.findByEmail(form.getEmail());
        if (user.getPazzd().equalsIgnoreCase(form.getTmpPwd())) {
            user.setPazzd(form.getNewPwd());
            user.setAccStatus(AppConstant.ACC_STATUS_UNLOCKED);
            userRepo.save(user);

            String to = user.getEmail();
            String sub = "Account Activation status.";
            String body = "Your account is successfully activated. \n \n Your new password is: " + user.getPazzd();
            emailUtils.emailSender(to, sub, body);
        }

        return true;
    }

    @Override
    public boolean accountLockStatus(LockForm form) {
        UserEntity user = userRepo.findByEmail(form.getEmail());
        if (AppConstant.ACC_STATUS_UNLOCKED.equals(user.getAccStatus())) {
            user.setAccStatus(AppConstant.ACC_STATUS_LOCKED);
            userRepo.save(user);
        }
        return true;
    }

    @Override
    public boolean forgotPwd(ForgotPwdSetter forgotPwdSetter) {
        UserEntity user = userRepo.findByEmail(forgotPwdSetter.getEmail());
        if (user == null) {
            throw new ApiException("Invalid Email address");
        }

        String to = user.getEmail();
        String subject = "Recover your Password.";
        String body = "Your password is: " + user.getPazzd();

        emailUtils.emailSender(to, subject, body);

        return true;
    }

    @Override
    public boolean accActiveSw(Integer userId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new ApiException("User Id not found"));

        if (user.getActiveSw().contains(AppConstant.ACTIVE_SW)) {
            user.setActiveSw(AppConstant.INACTIVE_SW);
            userRepo.save(user);
        } else if (user.getActiveSw().contains(AppConstant.INACTIVE_SW)) {
            user.setActiveSw(AppConstant.ACTIVE_SW);
            userRepo.save(user);
        }
        return true;
    }

    @Override
    public DashboardForm dashboardData() {
        long plansCount = planRepository.count();

        List<EligEntity> eligEntityList = eligRepository.findAll();
        long approvedCnt = eligEntityList.stream().filter(ed -> ed.getPlanStatus().equals("APPROVED")).count();
        long deniedCnt = eligEntityList.stream().filter(ed -> ed.getPlanStatus().equals("DENIED")).count();
        double totalBenefitAmtIssue = eligEntityList.stream().mapToDouble(ed -> ed.getBenefitAmt()).sum();

        DashboardForm dashboard = new DashboardForm();
        dashboard.setAvailablePlans(plansCount);
        dashboard.setCitizenApproved(approvedCnt);

        dashboard.setCitizenDenied(deniedCnt);
        dashboard.setBenefitGiven(totalBenefitAmtIssue);
        return dashboard;
    }


}
