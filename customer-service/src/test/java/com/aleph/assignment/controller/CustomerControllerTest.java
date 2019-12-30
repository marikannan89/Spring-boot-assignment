package com.aleph.assignment.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.aleph.assignment.model.Customer;
import com.aleph.assignment.persistance.entity.CustomerEntity;
import com.aleph.assignment.service.CusomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("Junit testcase of CustomerController")
@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CusomerService customerService;
	
	@Test
	@Order(1)
	@DisplayName("Create customer")
	public void testCreateCustomer() throws Exception {
		String uri = "/customers";
		CustomerEntity customerEntity = new CustomerEntity();
		Mockito.when(customerService.createCustomer(Mockito.any(Customer.class))).thenReturn(customerEntity);
		
		Customer customer = new Customer();
		customer.setIc("abdsfd23s441");
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
	@Order(2)
	@DisplayName("get customer")
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
	
	@Test
	@Order(3)
	@DisplayName("Find customer by IC")
	public void findCustomerbyIC() throws Exception {
		String uri = "/customers/ic/abdsfd23s441";
		CustomerEntity customer = new CustomerEntity();
		List<CustomerEntity> customerList = new ArrayList<>();
		customerList.add(customer);
		Mockito.when(customerService.getCustomer()).thenReturn(customerList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	@Order(4)
	@DisplayName("Find customer by age")
	public void findCustomerByAge() throws Exception {
		String uri = "/customers/age/30";
		CustomerEntity customer = new CustomerEntity();
		List<CustomerEntity> customerList = new ArrayList<>();
		customerList.add(customer);
		Mockito.when(customerService.getCustomer()).thenReturn(customerList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	@Order(5)
	@DisplayName("Find customer by name")
	public void findCustomerByName() throws Exception {
		String uri = "/customers/name/mari";
		CustomerEntity customer = new CustomerEntity();
		List<CustomerEntity> customerList = new ArrayList<>();
		customerList.add(customer);
		Mockito.when(customerService.getCustomer()).thenReturn(customerList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	@Order(6)
	@DisplayName("Update customer")
	public void testUpdateCustomer() throws Exception {
		String uri = "/customers/abdsfd23s441";
		CustomerEntity customerEntity = new CustomerEntity();
		Mockito.when(customerService.updateCustomer(Mockito.any(String.class),Mockito.any(Customer.class))).thenReturn(customerEntity);
		
		Customer customer = new Customer();
		customer.setIc("abdsfd23s441");
		customer.setName("mari_updated");
		customer.setAge("30");
		customer.setDob("21/04/1988");
		customer.setAddressLine1("Jalan ipoh,Sentul");
		customer.setAddressLine2("Kuala lumpur");
		String inputJson = this.mapToJson(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).accept(MediaType.APPLICATION_JSON)
				.content(inputJson).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	@Order(7)
	@DisplayName("Delete customer")
	public void testDeleteCustomer() throws Exception {
		String uri = "/customers/abdsfd23s441";
		CustomerEntity customerEntity = new CustomerEntity();
		Mockito.when(customerService.deleteCustomer(Mockito.any(String.class))).thenReturn(customerEntity);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	private String mapToJson(Customer customer) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(customer);
	}
}
