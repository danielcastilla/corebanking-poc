package org.dancas.customer.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.dancas.customer.payload.Customer;
import org.dancas.customer.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

private static final String CUSTOMER_JSON = "{ \"name\":\"Jose\", \"surname\":\"Fernandez\", \"age\":\"30\"}";

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CustomerService customerService;

	@Test
	public void whenNewCustomer_isCreatedWithUniqueId() throws Exception{
		
		when(customerService.create(any()))
			.thenReturn(new Customer().setId("1").setName("Jose"));
		
		mvc.perform(
				post("/v1/customer")
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.contentType(MediaType.APPLICATION_JSON)
					.content(CUSTOMER_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.name").value("Jose"));
				
	}

	@Test
	public void whenExistingCustomer_isReturned() throws Exception{
		
		when(customerService.getById(any()))
			.thenReturn(new Customer().setId("1"));
		
		mvc.perform(
				get("/v1/customer/10")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())	
		.andExpect(jsonPath("$.id").value("1"));
		
	}

	@Test
	public void whenExistingCustomer_isDeleted() throws Exception{
		when(customerService.deleteById(any()))
			.thenReturn(new String("1"));
		
		mvc.perform(
				delete("/v1/customer/1")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void whenExistingCustomer_returnAll() throws Exception{
		
		List<Customer> customers = Arrays.asList(
				new Customer("1", "Daniel", "Fernandez", "30"),
				new Customer("2", "Jose", "Fernandez", "40"));
		
		when(customerService.getAll())
			.thenReturn(customers);
			
		mvc.perform(
				get("/v1/customer")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is("1")))
            .andExpect(jsonPath("$[0].name", is("Daniel")))
            .andExpect(jsonPath("$[1].id", is("2")))
            .andExpect(jsonPath("$[1].name", is("Jose")));
	}
	
}
