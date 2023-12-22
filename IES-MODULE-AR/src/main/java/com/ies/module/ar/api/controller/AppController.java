package com.ies.module.ar.api.controller;

import com.ies.module.ar.api.bindings.AppForm;
import com.ies.module.ar.api.entities.AppEntity;
import com.ies.module.ar.api.exception.ApiException;
import com.ies.module.ar.api.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ies/AR-APP")
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/registerApp")
    public ResponseEntity<?> registerCitizen(@RequestBody AppForm form){
        boolean application = appService.createApplication(form);
        if (application){
            return new ResponseEntity<>("Citizen account created successfully..", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Citizen not belongs to Rhode Island..", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{caseNo}")
    public ResponseEntity<AppForm> fetchApp(@PathVariable String caseNo) throws Exception  {
        try {
            AppForm appForm = appService.findCitizen(caseNo);
            return ResponseEntity.status(HttpStatus.OK).body(appForm);
        }catch (Exception e){

           throw new ApiException("caseNo '"+caseNo+"' not found in the record..");
        }
    }
    @GetMapping("/viewCitizen")
    public ResponseEntity<List<AppForm>> getAllCitizen(){
        List<AppForm> appForms = appService.veiwCitizen();
        return ResponseEntity.ok(appForms);
    }







}
