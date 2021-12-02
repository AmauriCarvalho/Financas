package com.financas.FinancaTeste.controller;

import com.financas.FinancaTeste.model.ListaConta;
import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.service.ListaContaService;
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
@RequestMapping("/listaconta")
public class ListaContaController implements ControllerInterface<ListaConta>{

    @Autowired
    private ListaContaService service;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todas listas de contas cadastradas")})
    @Override
    @Operation(summary = "End point que devolve todas as listas de contas cadastradas")
    @GetMapping(produces = "Application/Json")
    public ResponseEntity<List<ListaConta>> getAll(){ return ResponseEntity.ok(service.findAll());}

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna a lista de conta cadastrada pelo número de ID"),
            @ApiResponse(responseCode = "404", description = "ID não existe para nenhuma conta")})
    @Override
    @Operation(summary = "End point que devolve uma lista de contas cadastrada pelo ID informado")
    @GetMapping(value = "/{id}", produces = "Application/Json")
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        ListaConta _listaConta = service.findById(id);
        if(_listaConta != null)
            return ResponseEntity.ok().body(_listaConta);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Lista de contas cadastrada"),
            @ApiResponse(responseCode = "401", description = "Requisição precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "Requisição com sintaxe inválida não cadastra a lista de contas ")
    })
    @Override
    @Operation(summary = "End point que cadastra uma lista conta")
    @PostMapping(produces = "Application/Json")
    public ResponseEntity<ListaConta> post(@RequestBody ListaConta listaConta) {
        service.create(listaConta);
        return ResponseEntity.ok(listaConta);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Altera a lista contas cadastradas"),
            @ApiResponse(responseCode = "401", description = "Requisição precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não altera a lista de contas ")
    })
    @PutMapping(path = {"/{id}"}, produces = "Application/Json")
    @Operation(summary = "End point que altera os dados de uma lista de contas")
    public ListaConta put(@RequestBody ListaConta listaConta, @PathVariable("id")long id){
        listaConta.setId(id);
        return service.update(listaConta);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deleta a lista de contas cadastrada"),
            @ApiResponse(responseCode = "401", description = "Requisição precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não deleta a lista de contas")
    })
    @Override
    @Operation(summary = "End point que deleta lista de contas cadastrada")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(service.delete(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
