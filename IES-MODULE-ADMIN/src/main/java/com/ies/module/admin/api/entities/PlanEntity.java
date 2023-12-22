package com.ies.module.admin.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "plans", schema = "ies_app")
public class PlanEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer planId;
	private String planName;
	private String category;
	private String planStartDate;
	private String planEndDate;
	private String accActiveSw;                    //make by default Y

	@Column(name = "CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;

	@Column(name = "UPDATE_DATE", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user;



}
