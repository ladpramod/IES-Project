package com.ies.module.admin.api.controller;

import com.ies.module.admin.api.bindings.PlanActiveSwitch;
import com.ies.module.admin.api.bindings.PlanForm;
import com.ies.module.admin.api.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ies/admin/plans")
@CrossOrigin
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")

    public ResponseEntity<String> planCreate(@RequestBody PlanForm planForm){
        boolean plan = planService.createPlan(planForm);
        if (plan){
            return new ResponseEntity<>("Plan created successfully..", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("UserId '"+planForm.getUserId()+"' not found..", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/viewPlans")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<PlanForm>> plansView()  {
        List<PlanForm> plans = planService.viewPlans();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/getplan/{planName}")
    public ResponseEntity<PlanForm> viewPlan(@PathVariable String planName){
        PlanForm plan = planService.getPlan(planName);
        return ResponseEntity.status(HttpStatus.OK).body(plan);
    }

    @PutMapping("/update/{planId}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")

    public ResponseEntity<String> planUpdate(@RequestBody PlanForm planForm,@PathVariable Integer planId){

        boolean plan = planService.updatePlan(planForm, planId);
        if (plan){
            return new ResponseEntity<>("Plan updated successfully..", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("planId '"+planId+"' is invalid",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/plan-sw/{planId}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> planSwitch(@PathVariable Integer planId){
        planService.activePlanAccSw(planId);
        return new ResponseEntity<>("Plan active switch status changed..",HttpStatus.OK);
    }


}
