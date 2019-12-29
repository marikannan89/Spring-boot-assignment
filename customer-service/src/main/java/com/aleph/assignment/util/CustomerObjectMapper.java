package com.aleph.assignment.util;

import org.mapstruct.Mapper;

import com.aleph.assignment.model.Customer;
import com.aleph.assignment.persistance.entity.CusomerEntity;

@Mapper
public interface CustomerObjectMapper {
	
	CusomerEntity toUserEntity (Customer user);

}
