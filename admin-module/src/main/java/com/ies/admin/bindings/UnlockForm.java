package com.ies.admin.bindings;

import lombok.Data;

@Data
public class UnlockForm {

    private String email;
    private String tmpPwd;
    private String newPwd;
    private String confirmPwd;
}
