package com.pankiba.restfulwebservices.service.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessUnitDTO {

	private Long businessUnitId;
	
	private String businessUnitName;
	
	private Set<EmployeeDTO> employeDTO = new HashSet<>();
	
}
