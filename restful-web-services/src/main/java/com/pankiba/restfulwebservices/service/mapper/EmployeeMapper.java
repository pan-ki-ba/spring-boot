package com.pankiba.restfulwebservices.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.pankiba.restfulwebservices.domain.Employee;
import com.pankiba.restfulwebservices.service.dto.EmployeeDTO;

@Mapper(componentModel = "spring", uses = { BusinessUnitMapper.class })
public interface EmployeeMapper extends MapStructMapper<EmployeeDTO, Employee> {

	EmployeeDTO toDto(Employee employee);
	
	List<EmployeeDTO> toDtoList(List<Employee> employeeList);

}
