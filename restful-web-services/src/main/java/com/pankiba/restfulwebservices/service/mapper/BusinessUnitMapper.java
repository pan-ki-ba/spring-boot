package com.pankiba.restfulwebservices.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pankiba.restfulwebservices.domain.BusinessUnit;
import com.pankiba.restfulwebservices.service.dto.BusinessUnitDTO;

@Mapper(componentModel = "spring", uses = { EmployeeMapper.class })
public interface BusinessUnitMapper extends MapStructMapper<BusinessUnitDTO, BusinessUnit> {

	@Mapping(source="employees", target = "employeDTO")
	BusinessUnitDTO toDto(BusinessUnit businessUnit);
	
	
}
