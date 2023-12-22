package com.ies.admin.entity;

import com.ies.admin.constants.AppConstant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "user_details")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String gender;
    private String dob;
    private Long mobileNo;
    private String houseNum;
    private String email;
    private String pazzd;
    private Long ssnNo; //Random no 1-9 length 9 digit
    private String accStatus = AppConstant.ACC_STATUS_LOCKED; //default LOCKED
    private String activeSw; //Default ACTIVE

    @Column(name = "CREATED_DATE", updatable = false)
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name = "UPDATED_DATE", insertable = false)
    @UpdateTimestamp
    private LocalDate updateDate;


    @OneToMany( mappedBy = "user",cascade = CascadeType.ALL)
    private List<PlanEntity> plans = new ArrayList<>() ;
}
