package com.ies.module.co.api.bindings;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoForm {

    private String noticeId;

    private String caseNo;

    private String planName;

    private String planStatus;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double benefitAmount;

    private Integer user;


}
