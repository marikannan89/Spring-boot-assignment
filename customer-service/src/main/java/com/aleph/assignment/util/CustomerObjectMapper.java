package com.aleph.assignment.util;

import org.mapstruct.Mapper;

import com.aleph.assignment.model.Customer;
import com.aleph.assignment.persistance.entity.CustomerEntity;

@Mapper
public interface CustomerObjectMapper {
	
	CustomerEntity toUserEntity (Customer user);

}
