package com.ies.module.ar.api.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "app")
public class AppEntity {

    @Id
    private String caseNo;
    private String fullName;
    private String email;
    private String gender;
    private String mobile;
    private String dob;
    private Long ssn;

    @Column(name = "CREATED_DATE", updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;

    @Column(name = "UPDATED_DATE", insertable = false)
    @CreationTimestamp
    private LocalDate updatedDate;

    @Column(name = "CREATED_BY")
    private Integer userId;

}
