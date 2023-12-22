package com.ies.admin.controller;

import com.ies.admin.entity.PlanEntity;
import com.ies.admin.exceptions.ApiResponse;
import com.ies.admin.bindings.PlanDto;
import com.ies.admin.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ies/plans")
public class PlanController {

    Logger logger = LoggerFactory.getLogger(PlanController.class);
    @Autowired
    private PlanService planService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPlan(@RequestBody PlanDto plan, @RequestParam("workerId") Integer workerId) {
        planService.savePlan(plan, workerId);
        return new ResponseEntity<>(new ApiResponse("Plan created successfully..", true), HttpStatus.CREATED);
    }

    @GetMapping("/view")
    public ResponseEntity<List<PlanDto>> planView() {
        List<PlanDto> planDtos = planService.viewPlans();
        return new ResponseEntity<>(planDtos, HttpStatus.OK);
    }

    @PatchMapping("/update/{planId}")
    public ResponseEntity<ApiResponse> planUpdate(@RequestBody PlanDto planDto, @PathVariable Integer planId) {
        planService.updatePlan(planDto, planId);
        return new ResponseEntity<>(new ApiResponse("Plan updated successfully..", true), HttpStatus.CREATED);
    }

    @PatchMapping("/state")
    public ResponseEntity<ApiResponse> activePlanSw(@RequestParam("planId") Integer planId) {
        planService.planActive(planId);
        return new ResponseEntity<>(new ApiResponse("Plan status changed successfully..", true), HttpStatus.OK);
    }
}
