package com.ies.module.ed.api.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "ELIG_DETLS")
public class EligEntity {

    @Id
    private String edgTraceId;

    private String planStatus;

    private Double benefitAmt;

    private String caseNo;

    private String planName;

    private LocalDate eligStartDate;

    private LocalDate eligEndDate;

    private String denialRsn;

}
