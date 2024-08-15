package io.github.rodrigojfagundes.minimarket.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.rodrigojfagundes.minimarket.dto.CategoryDTO;
import io.github.rodrigojfagundes.minimarket.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	

	
    @Operation(summary = "Buscar todas as Categories", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
	@GetMapping
	public ResponseEntity<Page<CategoryDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<CategoryDTO> categories = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(categories);
	}
	
    
    @Operation(summary = "Busca dados de category por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
		CategoryDTO category = service.findById(id);
		return ResponseEntity.ok().body(category);
	}
	
    
    @Operation(summary = "Realiza o cadastro de Category", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de Category realizado com sucesso"),  
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro de Category"),
    })
	@PostMapping
	public ResponseEntity<CategoryDTO> insert (@RequestBody CategoryDTO dto){
		CategoryDTO newDTO = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);
	}
	
    
    @Operation(summary = "Editar Category", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edição de Category realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "404", description = "Category não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao editar Category"),
    })
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
		CategoryDTO newDTO = service.update(id, dto);
		return ResponseEntity.ok().body(newDTO);
	}
	
    @Operation(summary = "Delete Category", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Category não encontrada"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar Category"),
    })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> delete (@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}