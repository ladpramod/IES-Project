package com.ies.module.dc.api.bindings;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DcSummary {
	
	private Income income;
	
	private Education education;
	
	private List<Child> childs;

}
