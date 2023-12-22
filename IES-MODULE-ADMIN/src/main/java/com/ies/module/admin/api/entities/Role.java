package com.ies.module.admin.api.entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "role")
public class Role {

    @Id
    private Integer roleId;
    private String roleName;
}
