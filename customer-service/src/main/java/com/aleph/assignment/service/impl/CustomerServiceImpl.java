package com.aleph.assignment.service.impl;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleph.assignment.exception.ResourceNotFoundException;
import com.aleph.assignment.model.Customer;
import com.aleph.assignment.persistance.dao.CustomerRepository;
import com.aleph.assignment.persistance.entity.CusomerEntity;
import com.aleph.assignment.service.CusomerService;
import com.aleph.assignment.util.CustomerObjectMapper;
import com.aleph.assignment.util.EncryptionUtil;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CusomerService {
	
	@Autowired 
	private CustomerRepository userRepository;
	
	CustomerObjectMapper userObjectMapper = Mappers.getMapper(CustomerObjectMapper.class);
	
	@Override
	public Iterable<CusomerEntity> getCustomer() {
		return userRepository.findAll(); 
	}
	
	@Override
	public CusomerEntity createCustomer(Customer customer) {
		return userRepository.save(userObjectMapper.toUserEntity(customer)); 
	}

	@Override
	public Object findCustomer(String fieldName, String value) {
		if("IC".equalsIgnoreCase(fieldName)) {
			return userRepository.findByIC(EncryptionUtil.encrypt(value)).orElseThrow(() -> new ResourceNotFoundException("Customer", "IC", value));
		} else if("Name".equalsIgnoreCase(fieldName)) {
			return userRepository.findByName(value).orElseThrow(() -> new ResourceNotFoundException("Customer", "Name", value));
		} else if ("Age".equalsIgnoreCase(fieldName)) {
			return userRepository.findByAge(value).orElseThrow(() -> new ResourceNotFoundException("Customer", "Age", value));
		} else {
			return userRepository.findById(Long.valueOf(value)).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", value));
		}
	}

	@Override
	public CusomerEntity updateCustomer(String ic, Customer customerDetails) {
		CusomerEntity customer = userRepository.findByIC(EncryptionUtil.encrypt(ic)).orElseThrow(() -> new ResourceNotFoundException("Customer", "ic", ic));
		CusomerEntity customerRequest = userObjectMapper.toUserEntity(customerDetails);
		customerRequest.setCustomerId(customer.getCustomerId());
        return userRepository.save(customerRequest);
	}

	@Override
	public CusomerEntity deleteCustomer(String ic) {
		CusomerEntity customer = userRepository.findByIC(EncryptionUtil.encrypt(ic)).orElseThrow(() -> new ResourceNotFoundException("Customer", "ic", ic));
		userRepository.delete(customer);
		return customer;
	}

}
