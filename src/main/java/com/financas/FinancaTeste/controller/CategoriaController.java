package com.financas.FinancaTeste.controller;

import com.financas.FinancaTeste.model.Categoria;
import com.financas.FinancaTeste.model.ListaProduto;
import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.service.CategoriaService;
import com.financas.FinancaTeste.service.ListaProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController implements ControllerInterface<Categoria> {
    @Autowired
    private CategoriaService service;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todos as categorias cadastradas")})
    @Override
    @Operation(summary = "End point que devolve uma lista de  categorias cadastradas")
    @GetMapping(produces = "Application/Json")
    public ResponseEntity<List<Categoria>> getAll(){ return ResponseEntity.ok(service.findAll());}

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna categoria cadastrada pelo número de ID"),
            @ApiResponse(responseCode = "404", description = "ID não existe para nenhuma categoria")})
    @Override
    @Operation(summary = "End point que devolve uma categoria cadastrada pelo ID informado")
    @GetMapping(value = "/{id}", produces = "Application/Json")
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        Categoria _categoria = service.findById(id);
        if(_categoria != null)
            return ResponseEntity.ok().body(_categoria);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Categoria cadastrada"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "Requisão com sintaxe inválida não cadastra a categoria ")
    })
    @Override
    @Operation(summary = "End point que cadastra uma categoria")
    @PostMapping(produces = "Application/Json")
    public ResponseEntity<Categoria> post(@RequestBody Categoria categoria) {
        service.create(categoria);
        return ResponseEntity.ok(categoria);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Altera categoria cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não altera a categoria")
    })
    @PutMapping(path = {"/{id}"}, produces = "Application/Json")
    @Operation(summary = "End point que altera os dados de uma categoria cadastrada")
    public Categoria put(@RequestBody Categoria categoria, @PathVariable("id")long id){
        categoria.setId(id);
        return service.update(categoria);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deleta a categoria cadastrada"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não deleta a categoria")
    })
    @Override
    @Operation(summary = "End point que deleta uma categoria cadastrada")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(service.delete(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
