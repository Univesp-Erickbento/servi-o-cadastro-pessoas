package com.mypet.core.adapters.in.controllers;

import com.mypet.core.pessoa.domain.Pessoa;
import com.mypet.core.pessoa.port.in.CadastrarPessoaUseCase;
import com.mypet.core.pessoa.port.in.ListarPessoasUseCase;
import com.mypet.core.pessoa.port.in.ObterPessoaUseCase;
import com.mypet.core.pessoa.port.in.AtualizarPessoaUseCase;
import com.mypet.core.pessoa.port.in.RemoverPessoaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = {"http://45.93.100.30:4200", "http://192.168.15.2:4200", "http://192.168.15.200:4200", "http://localhost:4200"})
public class PessoaController {

    private final ListarPessoasUseCase listarPessoasUseCase;
    private final ObterPessoaUseCase obterPessoaUseCase;
    private final CadastrarPessoaUseCase cadastrarPessoaUseCase;
    private final AtualizarPessoaUseCase atualizarPessoaUseCase;
    private final RemoverPessoaUseCase removerPessoaUseCase;

    public PessoaController(
            ListarPessoasUseCase listarPessoasUseCase,
            ObterPessoaUseCase obterPessoaUseCase,
            CadastrarPessoaUseCase cadastrarPessoaUseCase,
            AtualizarPessoaUseCase atualizarPessoaUseCase,
            RemoverPessoaUseCase removerPessoaUseCase) {
        this.listarPessoasUseCase = listarPessoasUseCase;
        this.obterPessoaUseCase = obterPessoaUseCase;
        this.cadastrarPessoaUseCase = cadastrarPessoaUseCase;
        this.atualizarPessoaUseCase = atualizarPessoaUseCase;
        this.removerPessoaUseCase = removerPessoaUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodas() {
        List<Pessoa> pessoas = listarPessoasUseCase.executar();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        return obterPessoaUseCase.obterPorId(id, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> buscarPorCpf(@PathVariable String cpf, @RequestHeader("Authorization") String authorizationHeader) {
        return obterPessoaUseCase.obterPorCpf(cpf, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa, @RequestHeader("Authorization") String authorizationHeader) {
        Pessoa pessoaSalva = cadastrarPessoaUseCase.executar(pessoa, authorizationHeader);
        return new ResponseEntity<>(pessoaSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa, @RequestHeader("Authorization") String authorizationHeader) {
        return atualizarPessoaUseCase.executar(id, pessoa, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        removerPessoaUseCase.executar(id, authorizationHeader);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
