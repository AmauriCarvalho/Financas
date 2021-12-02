package com.financas.FinancaTeste.controller;

import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.model.Servico;
import com.financas.FinancaTeste.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController implements ControllerInterface<Servico>{

    @Autowired
    private ServicoService service;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todos os serviços cadastrados")})
    @Override
    @Operation(summary = "End point que devolve uma lista de todos os servicos cadatrados")
    @GetMapping(produces = "Application/Json")
    public ResponseEntity<List<Servico>> getAll(){ return ResponseEntity.ok(service.findAll());}

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o serviço cadastrado pelo número de ID"),
            @ApiResponse(responseCode = "404", description = "ID não existe para nenhum produto")})
    @Override
    @Operation(summary = "End point que devolve um servico cadastrado pelo ID informado")
    @GetMapping(value = "/{id}", produces = "Application/Json")
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        Servico _servico = service.findById(id);
        if(_servico != null)
            return ResponseEntity.ok().body(_servico);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Serviço cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "Requisão com sintaxe inválida não cadastra o serviço ")
    })
    @Override
    @Operation(summary = "End point que cadastra um serviço")
    @PostMapping(produces = "Application/Json")
    public ResponseEntity<Servico> post(@RequestBody Servico servico) {
        service.create(servico);
        return ResponseEntity.ok(servico);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Altera o serviço cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não altera o serviço ")
    })
    @PutMapping(path = {"/{id}"}, produces = "Application/Json")
    @Operation(summary = "End point que altera os dados do servico")
    public Servico put(@RequestBody Servico servico, @PathVariable("id")long id){
        servico.setId(id);
        return service.update(servico);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deleta o serviço cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não deleta o serviço")
    })
    @Override
    @Operation(summary = "End point que deleta um servico")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(service.delete(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
