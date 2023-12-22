package com.ies.module.dc.api.controller;

import com.ies.module.dc.api.bindings.*;
import com.ies.module.dc.api.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ies/DC")
public class DcController {

    @Autowired
    private DcService service;

    @GetMapping("/getPlan")
    public ResponseEntity<List<String>> getPlanNames() {
        List<String> nameList = service.getPlansName();
        return ResponseEntity.ok(nameList);
    }

    @PostMapping("/setPlan")
    public ResponseEntity<String> PlanSelection(@RequestBody PlanSelection selectionPlan) {
        service.saveSelectedPlan(selectionPlan);
        return new ResponseEntity<>("Plan selected..", HttpStatus.CREATED);
    }

    @PostMapping("/saveIncome")
    public ResponseEntity<String> saveCitizenIncome(@RequestBody Income income) {
        service.saveIncomeData(income);
        return new ResponseEntity<>("Income details saved..", HttpStatus.CREATED);
    }

    @PostMapping("/saveEdu")
    public ResponseEntity<String> saveEduDetails(@RequestBody Education edu) {
        service.saveEducationData(edu);
        return new ResponseEntity<>("Education details saved..", HttpStatus.CREATED);
    }

    @PostMapping("/saveChilds")
    public ResponseEntity<String> saveKids(@RequestBody ChildReq req) {
        service.saveKidsData(req);
        return new ResponseEntity<>("Child details saved..", HttpStatus.CREATED);
    }

    @GetMapping("/getAllData/{caseNo}")
    public ResponseEntity<DcSummary> getCitizenDetails(@PathVariable String caseNo) {
        DcSummary summary = service.getSummary(caseNo);
        return ResponseEntity.status(HttpStatus.OK).body(summary);
    }

    @GetMapping("/getSinglePlan/{caseNo}")
    public ResponseEntity<PlanSelection> getCitizenPlan(@PathVariable String caseNo){
        PlanSelection plan = service.getPlan(caseNo);
        return ResponseEntity.status(HttpStatus.OK).body(plan);
    }

    @GetMapping("/getEducation/{caseNo}")
    public ResponseEntity<Education> getEducation(@PathVariable String caseNo){
        Education education = service.getEducation(caseNo);
        return ResponseEntity.status(HttpStatus.OK).body(education);
    }

    @GetMapping("/getIncome/{caseNo}")
    public ResponseEntity<Income> getIncome(@PathVariable String caseNo){
        Income income = service.getIncome(caseNo);
        return ResponseEntity.status(HttpStatus.OK).body(income);
    }

    @GetMapping("/getKids/{caseNo}")
    public ResponseEntity<ChildReq> getChilds(@PathVariable String caseNo){
        ChildReq child = service.getChild(caseNo);
        return ResponseEntity.status(HttpStatus.OK).body(child);
    }
}
