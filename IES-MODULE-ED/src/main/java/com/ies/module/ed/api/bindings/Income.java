package com.ies.module.ed.api.bindings;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Income {

	private String caseNo;
	private Double monthlySalaryIncome;
	private Double propertyIncome;
	private Double rentIncome;
	private Integer userId;
}
