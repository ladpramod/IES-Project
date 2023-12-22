package com.ies.module.dc.api.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PLAN_SELECTION")
public class PlanSelectionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer selectionId;

	@Column(name = "PLAN_NAME")
	private String planName;

	@Column(name = "CASE_NO")
	private String caseNo;

	@Column(name = "CREATED_BY")
	private Integer userId;
}