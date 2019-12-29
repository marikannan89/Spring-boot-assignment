package com.aleph.assignment.service;

import java.util.List;

import com.aleph.assignment.model.Customer;
import com.aleph.assignment.persistance.entity.CustomerEntity;

public interface CusomerService {

	List<CustomerEntity> getCustomer();
	
	CustomerEntity createCustomer(Customer customer);

	Object findCustomer(String fieldName, String value);

	CustomerEntity updateCustomer(String customerId, Customer customerDetails);

	CustomerEntity deleteCustomer(String customerId);

}
