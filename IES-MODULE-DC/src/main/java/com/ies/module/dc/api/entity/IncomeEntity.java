package com.ies.module.dc.api.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INCOME_DETAILS")
public class IncomeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer incomeId;
	@Column(name = "SALARY_INCOME")
	private Double monthlySalaryIncome;
	@Column(name = "PROPERTY_INCOME")
	private Double propertyIncome;
	@Column(name = "RENT_INCOME")
	private Double rentIncome;

	@Column(name = "CASE_NO")
	private String caseNo;

	@Column(name = "CREATED_BY")
	private Integer userId;

}
