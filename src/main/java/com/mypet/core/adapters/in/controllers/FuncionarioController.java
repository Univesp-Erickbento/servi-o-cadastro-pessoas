package com.mypet.mypet.adapters.in.controllers;

import com.mypet.mypet.domain.dto.FuncionarioDTO;
import com.mypet.mypet.userCase.FuncionarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = {"http://45.93.100.30:4200", "http://192.168.15.2:4200", "http://192.168.15.200:4200", "http://localhost:4200"})
public class FuncionarioController {

    @Autowired
    private FuncionarioServiceImpl funcionarioService;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarTodos(@RequestHeader("Authorization") String authorizationHeader) {
        List<FuncionarioDTO> funcionarios = funcionarioService.listarTodos(authorizationHeader);
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarPorId(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        return funcionarioService.buscarPorId(id, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<FuncionarioDTO> buscarPorPessoaId(@PathVariable Long pessoaId, @RequestHeader("Authorization") String authorizationHeader) {
        return funcionarioService.buscarPorPessoaId(pessoaId, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> salvar(@RequestBody FuncionarioDTO funcionarioDTO, @RequestHeader("Authorization") String authorizationHeader) {
        FuncionarioDTO funcionarioSalvo = funcionarioService.salvar(funcionarioDTO, authorizationHeader);
        return new ResponseEntity<>(funcionarioSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioDTO, @RequestHeader("Authorization") String authorizationHeader) {
        FuncionarioDTO funcionarioAtualizado = funcionarioService.atualizar(id, funcionarioDTO, authorizationHeader);
        return funcionarioAtualizado != null ? ResponseEntity.ok(funcionarioAtualizado) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        funcionarioService.deletar(id, authorizationHeader);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
