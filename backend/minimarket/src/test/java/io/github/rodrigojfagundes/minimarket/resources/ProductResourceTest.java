package io.github.rodrigojfagundes.minimarket.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.rodrigojfagundes.minimarket.dto.CategoryDTO;
import io.github.rodrigojfagundes.minimarket.dto.ProductDTO;
import io.github.rodrigojfagundes.minimarket.entities.Category;
import io.github.rodrigojfagundes.minimarket.entities.Product;
import io.github.rodrigojfagundes.minimarket.services.ProductService;

@WebMvcTest(ProductResource.class)
public class ProductResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Product product0;
	
	private ProductDTO productDTO0;
	
	private Category category0;
	
	private CategoryDTO categoryDTO0;
	
	private PageImpl<ProductDTO> page;
	
	@BeforeEach
	void setUp() throws Exception {
		
		category0 = new Category(1L,
				"name1",
				"description1");
		
		categoryDTO0 = new CategoryDTO(1L,
				"name1",
				"description1");
		
		product0 = new Product(null,
				"name1",
				"description",
				1L, category0);		
				
		productDTO0 = new ProductDTO(1L,
				"name",
				"description",
				1L, 
				1L
				);
		
		
		page = new PageImpl<>(List.of(productDTO0));				
		
	}
	
	@Test
	public void findAllShouldReturnPage() throws Exception {
		
		when(service.findAllPaged(any())).thenReturn(page);
		
		ResultActions result = mockMvc.perform(get("/products")
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		
	}
	
	@Test
	public void insertShouldReturnProductDTOCreated() throws Exception {
		
		when(service.inset(any())).thenReturn(productDTO0);
		
		String jsonBody = objectMapper.writeValueAsString(productDTO0);
		
		ResultActions result = 
				mockMvc.perform(post("/products")
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
		
		doNothing().when(service).delete(1L);
		
		ResultActions result = mockMvc.perform(delete("/products/{id}", 1L)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void findByIdShouldReturnProductWhenIdExists() throws Exception {
		
		when(service.findById(1L)).thenReturn(productDTO0);
		
		ResultActions result = mockMvc.perform(get("/products/{id}", 1L)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		
	}
	
	
	@Test
	public void updateShouldReturnProductDTOWhenIdExists() throws Exception {
		
		when(service.update(eq(1L), any())).thenReturn(productDTO0);
		
		String jsonBody = objectMapper.writeValueAsString(productDTO0);
		
		ResultActions result = mockMvc.perform(put("/products/{id}", 1L)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
	}
	
	
}