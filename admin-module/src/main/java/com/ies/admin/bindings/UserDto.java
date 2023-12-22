package com.ies.admin.bindings;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private String name;
    private String email;
    private String pazzd;
    private Long mobile;
    private String gender;
    private String dob;
    private Long ssn;
    private Integer roleId;




}
