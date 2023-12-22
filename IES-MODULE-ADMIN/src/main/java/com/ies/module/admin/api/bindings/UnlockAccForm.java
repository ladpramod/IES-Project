package com.ies.module.admin.api.bindings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnlockAccForm {

    private String email;
    private String temPazzwd;
    private String newPazz;
    private String accActiveSw;
    private String accStatus;

}
