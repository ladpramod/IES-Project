package com.ies.module.report.Bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChildReq {
	
	private String caseNo;
	private Integer userId;
	private List<Child> child;
}
