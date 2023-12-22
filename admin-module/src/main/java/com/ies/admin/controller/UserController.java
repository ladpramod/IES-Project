package com.ies.admin.controller;


import com.ies.admin.exceptions.ApiResponse;
import com.ies.admin.bindings.*;
import com.ies.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ies")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCaseWorkerAcc(@RequestBody UserDto userDto) {

        userService.createAccount(userDto);
        return new ResponseEntity<>(new ApiResponse("Account created successfully, please check email..", true), HttpStatus.CREATED);
    }

    @GetMapping("/view")
    public ResponseEntity<List<UserDto>> showCaseWorker() {
        List<UserDto> userDtos = userService.viewAccount();
        return new ResponseEntity<>(userDtos, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<ApiResponse> updateCaseWorkerAcc(@RequestBody UserDto userDto, @PathVariable Integer userId) {
        userService.updateAccount(userDto, userId);
        return new ResponseEntity<>(new ApiResponse("CaseWorker Account updated..", true), HttpStatus.ACCEPTED);
    }

    @PostMapping("/unlock")
    public ResponseEntity<ApiResponse> unlockAccStatus(@RequestBody UnlockForm form) {

        boolean status = userService.accountActiveStatus(form);
        if (status) {
            return new ResponseEntity<>(new ApiResponse("Account activated successfully..", true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("Invalid credentials..", false), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/locked")
    public ResponseEntity<ApiResponse> lockAccStatus(@RequestBody LockForm form) {
        userService.accountLockStatus(form);
        return new ResponseEntity<>(new ApiResponse("Account status changed as LOCKED..", true), HttpStatus.OK);
    }

    @PostMapping("switch")
    public ResponseEntity<ApiResponse> accSwitchStatus(@PathVariable Integer userId) {
        userService.accActiveSw(userId);

        return new ResponseEntity<>(new ApiResponse("Account switch status changed..", true), HttpStatus.OK);
    }

    @PostMapping("/forgot")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestBody ForgotPwdSetter forgotPwdSetter) {
        userService.forgotPwd(forgotPwdSetter);

        return new ResponseEntity<>(new ApiResponse("Password sent to your registered email..", true), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> accountLogin(@RequestBody LoginForm form) {
        boolean status = userService.login(form);
        if (status) {
            return new ResponseEntity<>(new ApiResponse("Login Successful..", true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("Invalid credentials/Account is LOCKED..", false), HttpStatus.BAD_REQUEST);
    }
}
