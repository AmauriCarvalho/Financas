package com.financas.FinancaTeste.controller;

import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.model.Usuario;
import com.financas.FinancaTeste.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuario")
public class UsuarioController implements ControllerInterface<Usuario>{

    @Autowired
    private UsuarioService service;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todos os usuários cadastrados")})
    @Override
    @Operation(summary = "End point que devolve uma lista de todos os usuários cadatrados")
    @GetMapping(produces = "Application/Json")
    public ResponseEntity<List<Usuario>> getAll(){ return ResponseEntity.ok(service.findAll());}

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o usuário cadastrado pelo número de ID"),
            @ApiResponse(responseCode = "404", description = "ID não existe para nenhum usuário")})
    @Override
    @Operation(summary = "End point que devolve usuário cadatrado pelo ID informado")
    @GetMapping(value = "/{id}", produces = "Application/Json")
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        Usuario _usuario = service.findById(id);
        if(_usuario != null)
            return ResponseEntity.ok().body(_usuario);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Usuário cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "Requisão com sintaxe inválida não cadastra o usuário ")
    })
    @Override
    @Operation(summary = "End point que cadatra um usuário")
    @PostMapping(produces = "Application/Json")
    public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
        service.create(usuario);
        return ResponseEntity.ok(usuario);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Altera o usuário cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não altera o usuário ")
    })
    @PutMapping(path = {"/{id}"}, produces = "Application/Json")
    @Operation(summary = "End point que altera os dados do úsuario")
    public Usuario put(@RequestBody Usuario usuario, @PathVariable("id")long id){
        usuario.setId(id);
        return service.update(usuario);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deleta o usuário cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não deleta o usuário")
    })
    @Override
    @Operation(summary = "End point que deleta o úsuario")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(service.delete(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
