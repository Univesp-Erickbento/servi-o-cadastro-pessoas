package com.mypet.mypet.controllers;

import com.mypet.mypet.domain.model.ClientesEntity;
import com.mypet.mypet.userCase.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = {"http://192.168.15.2:4200", "http://192.168.15.200:4200", "http://localhost:4200"})
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping
    public ResponseEntity<List<ClientesEntity>> listarTodos(@RequestHeader("Authorization") String authorizationHeader) {
        List<ClientesEntity> clientes = clienteService.listarTodos(authorizationHeader);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientesEntity> buscarPorId(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        return clienteService.buscarPorId(id, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ClientesEntity> salvar(@RequestBody ClientesEntity cliente, @RequestHeader("Authorization") String authorizationHeader) {
        ClientesEntity clienteSalvo = clienteService.salvar(cliente, authorizationHeader);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientesEntity> atualizar(@PathVariable Long id, @RequestBody ClientesEntity clientesEntity, @RequestHeader("Authorization") String authorizationHeader) {
        ClientesEntity clienteAtualizado = clienteService.atualizar(id, clientesEntity, authorizationHeader);
        return clienteAtualizado != null ? ResponseEntity.ok(clienteAtualizado) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        clienteService.deletar(id, authorizationHeader);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 🔹 Novo endpoint para buscar cliente por pessoaId
    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<ClientesEntity> buscarPorPessoaId(@PathVariable Long pessoaId,
                                                            @RequestHeader("Authorization") String authorizationHeader) {
        return clienteService.buscarPorPessoaId(pessoaId, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
