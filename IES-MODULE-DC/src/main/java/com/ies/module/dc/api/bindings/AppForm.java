package com.ies.module.dc.api.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppForm {

    private String caseNo;
    private String fullName;
    private String email;
    private String mobile;
    private String gender;
    private String dob;
    private Long ssn;
    private Integer userId;
    private LocalDate createdDate;
    private LocalDate updatedDate;

}
