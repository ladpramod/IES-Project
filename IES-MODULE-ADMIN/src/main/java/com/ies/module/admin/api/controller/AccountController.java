package com.ies.module.admin.api.controller;

import com.ies.module.admin.api.bindings.UserAccountForm;
import com.ies.module.admin.api.repository.UserRepository;
import com.ies.module.admin.api.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ies/admin")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired private AccountService accountService;
    @Autowired private UserRepository userRepository;
    @Autowired private AuthenticationManager authManager;


    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @PostMapping("/register")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> registerUserAccount(@RequestBody @Valid UserAccountForm accountForm) {

        Boolean account = accountService.createUserAccount(accountForm);
        if(account) {
        return new ResponseEntity<>("Account created successfully, please check email..", HttpStatus.CREATED);
        }
        else {
        	return new ResponseEntity<>("Account Creation Failed",HttpStatus.BAD_REQUEST);
		}
    }

    @GetMapping("/view")
//    @PreAuthorize("hasAuthority('ADMIN')")

    public ResponseEntity<List<UserAccountForm>> fetchUserAcc(){
        logger.debug("Fetching User Accounts process started..");
        List<UserAccountForm> viewUserAccount = accountService.viewUserAccount();
        logger.debug("Fetching User Accounts process completed..");
        logger.info("User Accounts Fetched success....");
        return new ResponseEntity<>(viewUserAccount, HttpStatus.OK);
    }

    @GetMapping("/view/{userId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserAccountForm> getUser(@PathVariable("userId") Integer userId) {
        UserAccountForm userAccById = accountService.getUserAccById(userId);
        logger.info("User account fetched successfully..");
        return new ResponseEntity<>(userAccById, HttpStatus.OK);
    }

    @PutMapping("/sw-status/{userId}")
//    @PreAuthorize("hasAuthority('ADMIN')")

    public ResponseEntity<String> switchStatus(@PathVariable Integer userId){
        logger.debug("Fetching userId in the DB process started..");
        accountService.activeSwStatus(userId);
        logger.info("status response sent to user..");

        return new ResponseEntity<>("Account status changed..",HttpStatus.OK);
    }












}
