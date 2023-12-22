package com.ies.module.dc.api.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KIDS_RECORD")
public class KidsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer kidsId;
	@Column(name = "KID_NAME")
	private String kidName;
	@Column(name = "KID_SSN")
	private Long kidSSN;
	@Column(name = "KID_AGE")
	private Integer kidAge;

	@Column(name = "CASE_NO")
	private String caseNo;
	@Column(name = "CREATED_BY")
	private Integer userId;

}
