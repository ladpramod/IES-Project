package com.ies.module.admin.api.response;

import lombok.Data;

@Data
public class JWTAuthRequest {

    private String username;
    private String password;
}
