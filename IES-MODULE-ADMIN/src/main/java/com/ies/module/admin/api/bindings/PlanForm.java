package com.ies.module.admin.api.bindings;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlanForm {

    private Integer planId;
    private String category;
    private String planName;
    private String planStartDate;
    private String planEndDate;
    private String accActiveSw;
    private Integer userId;


}
