package com.financas.FinancaTeste.controller;

import com.financas.FinancaTeste.model.ListaServico;
import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.service.ListaServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listaservico")
public class ControllerListaServico implements ControllerInterface<ListaServico>{
    @Autowired
    private ListaServicoService service;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todas as listas de serviços cadastradas")})
    @Override
    @Operation(summary = "End point que devolve todas as listas de serviços cadastradas")
    @GetMapping(produces = "Application/Json")
    public ResponseEntity<List<ListaServico>> getAll(){ return ResponseEntity.ok(service.findAll());}

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna a lista de serviço cadastrado pelo número de ID"),
            @ApiResponse(responseCode = "404", description = "ID não existe para nenhuma lista de serviço")})
    @Override
    @Operation(summary = "End point que devolve uma lista de serviços cadastrada pelo ID informado")
    @GetMapping(value = "/{id}", produces = "Application/Json")
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        ListaServico _listaServico = service.findById(id);
        if(_listaServico != null)
            return ResponseEntity.ok().body(_listaServico);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Lista de Serviço cadastrada"),
            @ApiResponse(responseCode = "401", description = "Requisição precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "Requisição com sintaxe inválida não cadastra a lista de serviço ")
    })
    @Override
    @Operation(summary = "End point que cadastra uma lista de serviços")
    @PostMapping(produces = "Application/Json")
    public ResponseEntity<ListaServico> post(@RequestBody ListaServico listaServico) {
        service.create(listaServico);
        return ResponseEntity.ok(listaServico);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Altera a lista serviço cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisição precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não altera a lista de serviço ")
    })
    @PutMapping(path = {"/{id}"}, produces = "Application/Json")
    @Operation(summary = "End point que altera os dados de uma lista de serviço cadastrado")
    public ListaServico put(@RequestBody ListaServico listaServico, @PathVariable("id")long id){
        listaServico.setId(id);
        return service.update(listaServico);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deleta a lista de serviço cadastrada"),
            @ApiResponse(responseCode = "401", description = "Requisição precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não deleta a lista de serviço")
    })
    @Override
    @DeleteMapping(value = "/{id}", produces = "Application/Json")
    @Operation(summary = "End point que deleta uma lista de serviço cadastrado")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(service.delete(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
