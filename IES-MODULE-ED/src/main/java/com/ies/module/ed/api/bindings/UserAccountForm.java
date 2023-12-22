package com.ies.module.ed.api.bindings;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserAccountForm {

    private Integer userId;
//    @NotNull (message = "Name should not null..")
    private String name;
//    @Email (message = "Invalid email entered..")
    private String email;
//    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number..")
    private String mobile;
//    @NotNull (message = "Gender should not null..")
    private String gender;
//    @NotNull (message = "Gender should not null..")
    private String dob;
    //@Pattern(regexp = "^\\d{9}$", message = "Invalid SSN number..")
    private Long ssn;
    private String activeSw;
    private String accStatus;
//    @NotNull (message = "Role should not null..")
    private String roles;
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
//            message = "Enter password in alphanumeric format (Ex. Abc@1234)")
    private String pzzwd;
}
