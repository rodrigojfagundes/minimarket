package io.github.rodrigojfagundes.minimarket.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.rodrigojfagundes.minimarket.dto.CategoryDTO;
import io.github.rodrigojfagundes.minimarket.services.CategoryService;
import io.github.rodrigojfagundes.minimarket.tests.Factory;

@WebMvcTest(CategoryResource.class)
public class CategoryResourceTest {	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoryService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	private CategoryDTO categoryDTO;
	//private PageImpl<CategoryDTO> page;
	
	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		
		categoryDTO = Factory.createCategoryDTO();
		List<CategoryDTO> categoriesDTO = List.of(categoryDTO);
		
		when(service.findAll()).thenReturn(categoriesDTO);
		when(service.findById(existingId)).thenReturn(categoryDTO);
		when(service.insert(any())).thenReturn(categoryDTO);
		when(service.update(eq(existingId), any())).thenReturn(categoryDTO);
		doNothing().when(service).delete(existingId);
		
	}
	
	@Test
	public void insertShouldReturnCategoryDTOCreated() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(categoryDTO);
		
		ResultActions result = mockMvc.perform(post("/categories")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
		
		ResultActions result =  mockMvc.perform(delete("/categories/{id}", existingId)
				.accept(MediaType.APPLICATION_JSON));
	result.andExpect(status().isNoContent());
	}
	
	@Test
	public void updateShouldReturnCategoryDTOWhenIdExists() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(categoryDTO);
		
		ResultActions result = mockMvc.perform(put("/categories/{id}", existingId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		
		
	}
	
	@Test
	public void findByIdShouldReturnCategoryWhenIdExist() throws Exception {
		
		ResultActions result = mockMvc.perform(get("/categories/{id}", existingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		
	}
	
	@Test
	public void findAllShouldReturnList() throws Exception {
		
		ResultActions result = mockMvc.perform(get("/categories")
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		
	}
	
}