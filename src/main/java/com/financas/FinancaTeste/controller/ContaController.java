package com.financas.FinancaTeste.controller;

import com.financas.FinancaTeste.model.Conta;
import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.service.ContaService;
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
@RequestMapping("/conta")
public class ContaController implements ControllerInterface<Conta>{

    @Autowired
    private ContaService service;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todos as contas cadastradas")})
    @Override
    @Operation(summary = "End point que devolve uma lista de todas as contas cadastradas")
    @GetMapping(produces = "Application/Json")
    public ResponseEntity<List<Conta>> getAll(){ return ResponseEntity.ok(service.findAll());}

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna a conta cadastrada pelo número de ID"),
            @ApiResponse(responseCode = "404", description = "ID não existe para nenhum conta")})
    @Override
    @Operation(summary = "End point que devolve uma conta cadastrada pelo ID informado")
    @GetMapping(value = "/{id}", produces = "Application/Json")
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        Conta _conta = service.findById(id);
        if(_conta != null)
            return ResponseEntity.ok().body(_conta);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Conta cadastrada"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "Requisão com sintaxe inválida não cadastra a conta")
    })
    @Override
    @Operation(summary = "End point que cadastra uma conta")
    @PostMapping(produces = "Application/Json")
    public ResponseEntity<Conta> post(@RequestBody Conta conta) {
        service.create(conta);
        return ResponseEntity.ok(conta);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Altera a conta cadastrada"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não altera a conta")
    })
    @PutMapping(path = {"/{id}"}, produces = "Application/Json")
    @Operation(summary = "End point que altera os dados de uma conta cadastrada")
    public Conta put(@RequestBody Conta conta,@PathVariable("id")long id){
        conta.setId(id);
        return service.update(conta);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deleta a conta cadastrada"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não deleta a conta")
    })
    @Override
    @Operation(summary = "End point que deleta uma conta cadatrada")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(service.delete(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
