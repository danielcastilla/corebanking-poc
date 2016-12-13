package org.dancas.product.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.dancas.product.payload.Product;
import org.dancas.product.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	private static final String PRODUCT_JSON = "{ \"id\":\"1\", \"name\":\"Cards\", \"description\":\"Product Card Description\"}";

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ProductService productService;
	
	@Test
	public void whenNewProduct_isCreatedWithUniqueId() throws Exception{
		
		when(productService.create(any()))
			.thenReturn(new Product().setId("1").setName("Cards"));
		
		mvc.perform(
				post("/v1/product")
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.contentType(MediaType.APPLICATION_JSON)
					.content(PRODUCT_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.name").value("Cards"));
	}
	
	@Test
	public void whenExistingProduct_isReturned() throws Exception{
		
		when(productService.getById(any()))
			.thenReturn(new Product().setId("1"));
		
		mvc.perform(
				get("/v1/product/1")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())	
		.andExpect(jsonPath("$.id").value("1"));
	}
	
	@Test
	public void whenExistingProducts_returnAll() throws Exception{
		
		List<Product> products = Arrays.asList(
				new Product("1", "Card", "Cards description"),
				new Product("2", "Account", "Account description"));
		
		when(productService.getAll())
			.thenReturn(products);
			
		mvc.perform(
				get("/v1/product")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is("1")))
            .andExpect(jsonPath("$[0].name", is("Card")))
            .andExpect(jsonPath("$[1].id", is("2")))
            .andExpect(jsonPath("$[1].name", is("Account")));
	}

}
