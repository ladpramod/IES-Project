package com.ies.module.dc.api.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EDUCATION_DETAILS")

public class EducationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer educationId;
	@Column(name = "HIGH_DEGREE")
	private String highestDegree;
	@Column(name = "GRAD_YEAR")
	private String graduationYear;
	@Column(name = "UNIVERSITY_NAME")
	private String universityName;

	@Column(name = "CASE_NO")
	private String caseNo;

	@Column(name = "CREATED_BY")
	private Integer userId;

}
