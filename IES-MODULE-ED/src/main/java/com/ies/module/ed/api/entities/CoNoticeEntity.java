package com.ies.module.ed.api.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CO_NOTICES")
public class CoNoticeEntity {

	@Id

	private String noticeId;

	private String caseNum;

	private String noticeStatus;

	private String noticeUrl;

	private Integer edgTraceId;

	@CreationTimestamp
	private LocalDate noticePrintDate;


}
