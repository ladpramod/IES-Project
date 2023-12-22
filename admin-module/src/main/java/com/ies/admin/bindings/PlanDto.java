package com.ies.admin.bindings;

import com.ies.admin.constants.AppConstant;
import com.ies.admin.entity.UserEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanDto {

    private String planName;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
    private String activeSw = AppConstant.ACTIVE_SW;
    private LocalDate createDate;
    private LocalDate updateDate;
    private UserEntity userId;

}
