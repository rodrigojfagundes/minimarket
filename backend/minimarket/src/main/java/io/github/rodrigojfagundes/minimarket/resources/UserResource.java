package io.github.rodrigojfagundes.minimarket.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.rodrigojfagundes.minimarket.dto.UserDTO;
import io.github.rodrigojfagundes.minimarket.dto.UserInsertDTO;
import io.github.rodrigojfagundes.minimarket.dto.UserUpdateDTO;
import io.github.rodrigojfagundes.minimarket.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	
    @Operation(summary = "Busca dados de todos usuários", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
	@GetMapping
	private ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> usersDTOs = service.findAll();
		return ResponseEntity.ok().body(usersDTOs);
	}
	
    
    @Operation(summary = "Busca dados de User por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "401", description = "Aceso não autorizado"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id){
		UserDTO userDTO = service.findById(id);
		return ResponseEntity.ok(userDTO);
	}
	    
	   @Operation(summary = "Realiza o cadastro de User", method = "POST")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "201", description = "Cadastro de User realizado com sucesso"),  
	            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
	            @ApiResponse(responseCode = "422", description = "Solicitação foi bem formada, mas não pôde ser atendida devido a erros semânticos"),
	            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro de User"),
	    })
	@PostMapping 
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO userInsertDTO){
		UserDTO userDTO = service.insert(userInsertDTO);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}").buildAndExpand(
						userDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(userDTO);
	}
	
	
	    @Operation(summary = "Editar User", method = "PUT")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Edição de User realizada com sucesso"),
	            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
	            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
	            @ApiResponse(responseCode = "404", description = "User não encontrado"),
	            @ApiResponse(responseCode = "500", description = "Erro ao editar User"),
	    })
	@PutMapping
	public ResponseEntity<UserDTO> update(@PathVariable Long id, 
			@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
		
		UserDTO userDTO = service.update(id, userUpdateDTO);
		return ResponseEntity.ok().body(userDTO);
	}
	
	
	    @Operation(summary = "Delete User", method = "DELETE")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "204", description = "User deletado com sucesso"),
	            @ApiResponse(responseCode = "404", description = "User não encontrada"),
	            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
	            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
	            @ApiResponse(responseCode = "500", description = "Erro ao deletar User"),
	    })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Long id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}