package com.ies.module.admin.api.bindings;

import com.ies.module.admin.api.entities.Role;
import lombok.*;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserAccountForm {

    private Integer userId;
    @NotBlank(message = "Name should not null..")
    private String name;
    @Email (message = "Invalid email entered..")
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number..")
    private String mobile;
    @NotBlank (message = "Gender should not null..")
    private String gender;
    @NotEmpty (message = "DoB should not null..")
    private String dob;
    private Long ssn;
    private String activeSw;
    private String accStatus;
    private String roleName;
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
//            message = "Enter password in alphanumeric format (Ex. Abc@1234)")
//    private String pzzwd;
}
