package com.aleph.assignment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleph.assignment.model.Customer;
import com.aleph.assignment.persistance.entity.CustomerEntity;
import com.aleph.assignment.service.CusomerService;

@RestController
public class CustomerController {
	
	@Autowired
    CusomerService customerService;
	
	@GetMapping(path = "/customers")
    public ResponseEntity<?> getCustomer() {
        List<CustomerEntity> response = customerService.getCustomer();
        return new ResponseEntity<List<CustomerEntity>>(response, HttpStatus.OK);
    }
	
	@PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer) {
		CustomerEntity response = customerService.createCustomer(customer);
        return new ResponseEntity<CustomerEntity>(response, HttpStatus.CREATED);
    }
	
	@GetMapping("/customers/{fieldName}/{value}")
    public ResponseEntity<?> findCustomer(@PathVariable(value = "fieldName") String fieldName, @PathVariable(value = "value") String value) {
		Object response = customerService.findCustomer(fieldName,value);
		if(response instanceof List<?>) {
			 return new ResponseEntity<List<CustomerEntity>>((List<CustomerEntity>) response, HttpStatus.OK);
		}
        return new ResponseEntity<CustomerEntity>((CustomerEntity) response, HttpStatus.OK);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable(value = "id") String customerId, @Valid @RequestBody Customer customerDetails) {
    	CustomerEntity response = customerService.updateCustomer(customerId,customerDetails);
        return new ResponseEntity<CustomerEntity>(response, HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") String customerId) {
    	CustomerEntity response = customerService.deleteCustomer(customerId);
        return new ResponseEntity<CustomerEntity>(response, HttpStatus.OK);
    }

}
