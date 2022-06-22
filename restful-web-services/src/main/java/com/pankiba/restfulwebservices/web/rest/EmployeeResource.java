package com.pankiba.restfulwebservices.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankiba.restfulwebservices.domain.Employee;
import com.pankiba.restfulwebservices.service.EmployeeService;
import com.pankiba.restfulwebservices.service.dto.EmployeeDTO;
import com.pankiba.restfulwebservices.service.mapper.EmployeeMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeResource {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeMapper employeeMapper;

	/**
	 * Retrieve All Employees
	 * 
	 * @return
	 */
	@GetMapping(path = "/employees")
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		log.info("Fetching all employees");
		List<Employee> employeeList = employeeService.getEmployees();
		if (employeeList.isEmpty()) {
			return new ResponseEntity<List<EmployeeDTO>>(HttpStatus.NO_CONTENT);
		}
		
		List<EmployeeDTO> employeeDTOList =  employeeMapper.toDtoList(employeeList);
		
		log.info(" {} ",employeeDTOList);
		
		return new ResponseEntity<List<EmployeeDTO>>(employeeDTOList, HttpStatus.OK);
	}
	
}
