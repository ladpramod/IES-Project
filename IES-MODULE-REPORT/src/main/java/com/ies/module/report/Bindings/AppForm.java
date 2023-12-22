package com.ies.module.report.Bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
