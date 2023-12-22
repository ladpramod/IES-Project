package com.ies.module.ar.api.bindings;

import com.ies.module.ar.api.entities.UserAccountForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
