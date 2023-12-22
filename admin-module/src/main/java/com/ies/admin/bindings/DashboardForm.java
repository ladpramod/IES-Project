package com.ies.admin.bindings;

import lombok.Data;

@Data
public class DashboardForm {

    private Long availablePlans;
    private Long citizenApproved;
    private Long citizenDenied;
    private Double benefitGiven;
}
