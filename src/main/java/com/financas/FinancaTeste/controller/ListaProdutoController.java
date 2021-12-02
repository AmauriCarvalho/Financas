package com.financas.FinancaTeste.controller;

import com.financas.FinancaTeste.model.ListaProduto;
import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.service.ListaProdutoService;
import com.financas.FinancaTeste.service.ProdutoService;
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
@RequestMapping("/listaproduto")
public class ListaProdutoController implements ControllerInterface<ListaProduto>{
    @Autowired
    private ListaProdutoService service;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todas as listas de produtos cadastradas")})
    @Override
    @Operation(summary = "End point que devolve todas as listas de produtos cadatrados")
    @GetMapping(produces = "Application/Json")
    public ResponseEntity<List<ListaProduto>> getAll(){ return ResponseEntity.ok(service.findAll());}

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna a lista de produtos cadastrado pelo número de ID"),
            @ApiResponse(responseCode = "404", description = "ID não existe para nenhuma lista de produto")})
    @Override
    @Operation(summary = "End point que devolve uma lista de produtos cadastrada pelo ID informado")
    @GetMapping(value = "/{id}", produces = "Application/Json")
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        ListaProduto _listaProduto = service.findById(id);
        if(_listaProduto != null)
            return ResponseEntity.ok().body(_listaProduto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Lista de produtos cadastrada"),
            @ApiResponse(responseCode = "401", description = "Requisição precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "Requisição com sintaxe inválida não cadastra a lista de produtos ")
    })
    @Override
    @Operation(summary = "End point que cadastra uma lista de produtos")
    @PostMapping(produces = "Application/Json")
    public ResponseEntity<ListaProduto> post(@RequestBody ListaProduto listaProduto) {
        service.create(listaProduto);
        return ResponseEntity.ok(listaProduto);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Altera a lista produtos cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisição precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não altera a lista de produtos ")
    })
    @PutMapping(path = {"/{id}"}, produces = "Application/Json")
    @Operation(summary = "End point que altera os dados de uma lista de produtos")
    public ListaProduto put(@RequestBody ListaProduto listaProduto, @PathVariable("id")long id){
        listaProduto.setId(id);
        return service.update(listaProduto);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deleta a lista de produtos cadastrada"),
            @ApiResponse(responseCode = "401", description = "Requisição precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não deleta a lista de produtos")
    })
    @Override
    @Operation(summary = "End point que deleta um lista de produtos")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(service.delete(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
