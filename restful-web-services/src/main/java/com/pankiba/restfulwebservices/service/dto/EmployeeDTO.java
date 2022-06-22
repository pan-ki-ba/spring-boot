package com.pankiba.restfulwebservices.service.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pankiba.restfulwebservices.domain.Grade;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDTO {

	private Long employeeId;

	private String firstName;

	private String lastName;

	private String gender;

	private String email;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Temporal(TemporalType.DATE)
	private Date joiningDate;
	
	private Grade grade;

	private Long salary;
}
