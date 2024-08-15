package io.github.rodrigojfagundes.minimarket.resources;

import java.net.URI;

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

import io.github.rodrigojfagundes.minimarket.dto.ProductDTO;
import io.github.rodrigojfagundes.minimarket.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
    @Operation(summary = "Buscar todos os Products", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findAll(
			@RequestParam(value = "categoryId", defaultValue = "0")Long categoryId,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ProductDTO> products = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(products);
	}
	
    @Operation(summary = "Busca dados de Product por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
		ProductDTO product = service.findById(id);
		return ResponseEntity.ok().body(product);
	}
	
    @Operation(summary = "Realiza o cadastro de Product", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de Product realizado com sucesso"),  
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro de Product"),
    })
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
		ProductDTO newDTO = service.inset(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).body(newDTO);
	}
	
    @Operation(summary = "Editar Product", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edição de Product realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "404", description = "Product não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao editar Product"),
    })
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update (@PathVariable Long id, @RequestBody ProductDTO dto) {
		ProductDTO newDTO = service.update(id, dto);
		return ResponseEntity.ok().body(newDTO);
	}
	
    @Operation(summary = "Delete Product", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Product não encontrado"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar Product"),
    })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
