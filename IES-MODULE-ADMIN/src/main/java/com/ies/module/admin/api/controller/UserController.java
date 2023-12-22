package com.ies.module.admin.api.controller;

import com.ies.module.admin.api.bindings.DashboardCards;
import com.ies.module.admin.api.bindings.LoginForm;
import com.ies.module.admin.api.bindings.UnlockAccForm;
import com.ies.module.admin.api.bindings.UserAccountForm;
import com.ies.module.admin.api.constants.AppConstants;
import com.ies.module.admin.api.entities.Role;
import com.ies.module.admin.api.entities.User;
import com.ies.module.admin.api.exception.ApiException;
import com.ies.module.admin.api.repository.UserRepository;
import com.ies.module.admin.api.response.JWTAuthRequest;
import com.ies.module.admin.api.response.JWTAuthResponse;
import com.ies.module.admin.api.security.JWTTokenHelper;
import com.ies.module.admin.api.security.UserInfoUserDetailsService;
import com.ies.module.admin.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ies/admin")
@CrossOrigin(origins = "*")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private  AuthenticationManager authenticationManager;
    @Autowired private JWTTokenHelper jwtTokenHelper;
    @Autowired private UserInfoUserDetailsService userDetailsService;




    @PostMapping("/login")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JWTAuthResponse> createToken(@RequestBody LoginForm loginForm) throws Exception {

        this.authenticate(loginForm.getUsername(), loginForm.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginForm.getUsername());

        String token = this.jwtTokenHelper.generateToken(userDetails);

        JWTAuthResponse response = new JWTAuthResponse();
        response.setToken(token);

        return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        try {
            this.authenticationManager.authenticate(authenticationToken);
        }
        catch (BadCredentialsException e){
            System.out.println("Invalid login details!!");
            throw new ApiException("Invalid credentials!!");
        }
    }

//    @PostMapping("/login")
////    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//
//    public String UserLogin(@RequestBody LoginForm loginForm){
//
//            userService.login(loginForm);
//        return "redirect:/dashboard";
//    }

    @PostMapping("/unlock")
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<String> unlockUser(@RequestBody UnlockAccForm accForm) throws Exception{
        try {
            userService.unlockUserAccount(accForm);
            return new ResponseEntity<>("User account unlocked successfully, please check mail..", HttpStatus.OK);
        }catch (Exception e){
            throw new ApiException("Invalid credentials..");
        }

    }

    @PostMapping("/recover")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<String> forgotPazzwd(@RequestBody LoginForm loginForm){
        boolean user = userService.recoverPwd(loginForm);
        if (user){
            return new ResponseEntity<>("Password Sent successfully, please check mail..", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid email..", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dashboard")
//    @Secured("ADMIN")
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")

    public ResponseEntity<DashboardCards> buildDashboard(@RequestParam String email) {

        UserAccountForm userEmail = userService.findByUserEmail(email);
        DashboardCards dashboardCard = userService.fetchDashboardInfo();
        dashboardCard.setUser(userEmail);
        return ResponseEntity.ok(dashboardCard);
    }

    //Authorization
//    @GetMapping("/access/{userId}/{roles}")
//    @Secured("ADMIN")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public String giveUserAccess(@PathVariable Integer userId,@PathVariable String roles, Principal principal){
//
//        User user = userRepository.findById(userId).get();
//        List<String> activeRoles = getRolesByLoggedInUser(principal);
//        Role role = new Role();
//        if (activeRoles.contains(roles)){
//            newRoles = user.getRoles() + "," + roles;
//            user.getRoles().add(newRoles)
//        }
//        userRepository.save(user);
//        return "Access changed for User: "+userId;
//    }
//
//    //Method for role Based Authorization
//    private List<String> getRolesByLoggedInUser(Principal principal){
//        String role = getLoggedInUser(principal).getRoles();
//        List<String> assignedRoles = Arrays.stream(role.split(",")).collect(Collectors.toList());
//        if (assignedRoles.contains("ADMIN")){
//            return Arrays.stream(AppConstants.ADMIN).collect(Collectors.toList());
//        }
//        if (assignedRoles.contains("USER")){
//            return Arrays.stream(AppConstants.CASEWORKER).collect(Collectors.toList());
//        }
//        return Collections.emptyList();
//    }
//    private User getLoggedInUser(Principal principal){
//        return userRepository.findByEmail(principal.getName()).get();
//    }



}
