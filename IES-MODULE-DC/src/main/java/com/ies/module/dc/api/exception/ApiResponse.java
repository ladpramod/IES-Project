package com.ies.module.dc.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private boolean status;
    private LocalDateTime date;
}
