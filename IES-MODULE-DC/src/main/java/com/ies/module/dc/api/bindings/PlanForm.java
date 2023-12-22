package com.ies.module.dc.api.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanForm {
    private Integer planId;
    private Integer userId;
    private String category;
    private String planName;
//    private LocalDate planStartDate;
//    private LocalDate planEndDate;
    private String accActiveSw;

}
