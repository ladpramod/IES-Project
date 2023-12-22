package com.ies.admin.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ELIG_DETLS")
public class EligEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer edgTraceId;

    private String planStatus;

    private Double benefitAmt;
}
