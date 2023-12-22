package com.ies.admin.entity;

import com.ies.admin.constants.AppConstant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "plane_data")
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planId;
    private String planName;
    private String category;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(name = "ACTIVE_SW")
    private String activeSw = AppConstant.ACTIVE_SW;
    @Column(name = "CREATED_DATE", updatable = false)
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name = "UPDATE_DATE", insertable = false)
    @UpdateTimestamp
    private LocalDate updateDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "createdBy")
    private UserEntity user;



}
