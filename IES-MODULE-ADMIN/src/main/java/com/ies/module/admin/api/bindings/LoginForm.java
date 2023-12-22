package com.ies.module.admin.api.bindings;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    private String username;
    private String password;
}
