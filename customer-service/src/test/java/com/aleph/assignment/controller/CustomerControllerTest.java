package com.aleph.assignment.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.aleph.assignment.model.Customer;
import com.aleph.assignment.persistance.entity.CustomerEntity;
import com.aleph.assignment.service.CusomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CusomerService customerService;
	
	@Test
	public void testCreateCustomer() throws Exception {
		String uri = "/customers";
		CustomerEntity customerEntity = new CustomerEntity();
		Mockito.when(customerService.createCustomer(Mockito.any(Customer.class))).thenReturn(customerEntity);
		
		Customer customer = new Customer();
		customer.setIc("abdsfd23s440");
		customer.setName("mari");
		customer.setAge("30");
		customer.setDob("21/04/1988");
		customer.setAddressLine1("Jalan ipoh,Sentul");
		customer.setAddressLine2("Kuala lumpur");
		String inputJson = this.mapToJson(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.content(inputJson).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void testGetCustomer() throws Exception {
		String uri = "/customers";
		CustomerEntity customer = new CustomerEntity();
		List<CustomerEntity> customerList = new ArrayList<>();
		customerList.add(customer);
		Mockito.when(customerService.getCustomer()).thenReturn(customerList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	private String mapToJson(Customer customer) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(customer);
	}
}
