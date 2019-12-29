package com.aleph.assignment.service;

import com.aleph.assignment.model.Customer;
import com.aleph.assignment.persistance.entity.CusomerEntity;

public interface CusomerService {

	Iterable<CusomerEntity> getCustomer();
	
	CusomerEntity createCustomer(Customer customer);

	Object findCustomer(String fieldName, String value);

	CusomerEntity updateCustomer(String customerId, Customer customerDetails);

	CusomerEntity deleteCustomer(String customerId);

}
