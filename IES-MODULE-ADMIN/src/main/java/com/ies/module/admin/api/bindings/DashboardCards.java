package com.ies.module.admin.api.bindings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardCards {

    private Long plansCnt;
    private Long approvedCnt;
    private Long deniedCnt;
    private Double benefitAmtGiven;

    private UserAccountForm user;
}
