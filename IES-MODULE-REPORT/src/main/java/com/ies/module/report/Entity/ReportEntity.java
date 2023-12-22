package com.ies.module.report.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="reports")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer reportId;

    private String caseNo;

    private String gender;

    private String applicantName;

    private String email;

    private String mobile;

    private Long ssn;

    private String planName;

    private String planStatus;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double benefitAmount;

    private String denielReason;


}
