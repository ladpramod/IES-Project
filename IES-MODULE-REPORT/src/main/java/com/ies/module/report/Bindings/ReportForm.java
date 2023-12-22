package com.ies.module.report.Bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportForm {

    private String gender;

    private String applicantName;

    private String planName;

    private String planStatus;

    private String startDate;

    private String endDate;

    private Double benefitAmount;


}
