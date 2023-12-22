package com.ies.module.ed.api.bindings;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EdResponse {

	private String caseNo;

	private String planName;

	private String planStatus;

	private LocalDate eligStartDate;

	private LocalDate eligEndDate;

	private Double benefitAmt;

	private String denialRsn;



}
