package com.ies.module.ed.api.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
