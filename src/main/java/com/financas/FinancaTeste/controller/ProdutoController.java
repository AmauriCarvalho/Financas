package com.financas.FinancaTeste.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.service.ProdutoService;



import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/produto")
public class ProdutoController implements ControllerInterface<Produto> {

    @Autowired
    private ProdutoService service;

    @Override
    @Operation(summary = "End point que devolve uma lista de todos os produtos cadatrados")
    @GetMapping(produces = "Application/Json")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todos os produtos cadastrados")})
    public ResponseEntity<List<Produto>> getAll(){ return ResponseEntity.ok(service.findAll());}

    @Override
    @Operation(summary = "End point que devolve produto pelo ID informado no parâmetro")
    @GetMapping(value = "/{id}" ,produces = "Application/Json")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o produto cadastrado pelo número de ID"),
            @ApiResponse(responseCode = "404", description = "ID não existe para nenhum produto")})
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        Produto _produto = service.findById(id);
        if(_produto != null)
            return ResponseEntity.ok().body(_produto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Produto cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "Requisão com sintaxe inválida não cadastra o produto ")
    })
    @Override
    @Operation(summary = "End point que cadastra um produto")
    @PostMapping(produces = "Application/Json")
    public ResponseEntity<Produto> post(@RequestBody Produto produto) {
        service.create(produto);
        return ResponseEntity.ok(produto);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Altera o produto cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não altera o produto ")
    })
    @Operation(summary = "End point que atualiza os dados de um produto cadastrado")
    @PutMapping(path = {"/{id}"}, produces = "Application/Json")
    public Produto put(@RequestBody Produto produto,@PathVariable("id")long id){
            produto.setId(id);
            return service.update(produto);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deleta o produto cadastrado"),
            @ApiResponse(responseCode = "401", description = "Requisão precisa de autenticação com login"),
            @ApiResponse(responseCode = "404", description = "ID inválido não deleta o produto")
    })
    @Override
    @Operation(summary = "End point que deleta o produto")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(service.delete(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
