package com.mypet.mypet.adapters.in.controllers;

import com.mypet.mypet.application.core.domain.model.PessoasEntity;
import com.mypet.mypet.userCase.PessoaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = {"http://45.93.100.30:4200", "http://192.168.15.2:4200", "http://192.168.15.200:4200", "http://localhost:4200"})
public class PessoaController {

    @Autowired
    private PessoaServiceImpl pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoasEntity>> listarTodas() {
        List<PessoasEntity> pessoas = pessoaService.listarTodas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoasEntity> buscarPorId(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        return pessoaService.buscarPorId(id, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoasEntity> buscarPorCpf(@PathVariable String cpf, @RequestHeader("Authorization") String authorizationHeader) {
        return pessoaService.buscarPorCpf(cpf, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<PessoasEntity> salvar(@RequestBody PessoasEntity pessoa, @RequestHeader("Authorization") String authorizationHeader) {
        PessoasEntity pessoaSalva = pessoaService.salvar(pessoa, authorizationHeader);
        return new ResponseEntity<>(pessoaSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoasEntity> atualizar(@PathVariable Long id, @RequestBody PessoasEntity pessoa, @RequestHeader("Authorization") String authorizationHeader) {
        PessoasEntity pessoaAtualizada = pessoaService.atualizar(id, pessoa, authorizationHeader);
        return pessoaAtualizada != null ? ResponseEntity.ok(pessoaAtualizada) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        pessoaService.deletar(id, authorizationHeader);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
