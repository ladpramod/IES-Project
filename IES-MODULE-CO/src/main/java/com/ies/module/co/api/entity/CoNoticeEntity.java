package com.ies.module.co.api.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "CO_NOTICES")
public class CoNoticeEntity {

	@Id

	private String noticeId;

	private String caseNo;

	private String planName;

	private String planStatus;

	private LocalDate startDate;

	private LocalDate endDate;

	private Double benefitAmount;

	private String denielReason;

	@CreationTimestamp
	private LocalDate noticePrintDate;

	@Column(name = "CreatedBy")
	private Integer user;


}
