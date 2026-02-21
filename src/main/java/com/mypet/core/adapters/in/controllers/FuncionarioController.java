package com.mypet.core.adapters.in.controllers;

import com.mypet.core.funcionario.domain.Funcionario;
import com.mypet.core.funcionario.port.in.*;
import com.mypet.core.pessoa.domain.PessoaId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = {
        "http://45.93.100.30:4200",
        "http://192.168.15.2:4200",
        "http://192.168.15.200:4200",
        "http://localhost:4200"
})
public class FuncionarioController {

    private final CadastrarFuncionarioUseCase cadastrarFuncionarioUseCase;
    private final ListarFuncionarioUseCase listarFuncionarioUseCase;
    private final ObterFuncionarioUseCase obterFuncionarioUseCase;
    private final ObterFuncionarioPorPessoaUseCase obterFuncionarioPorPessoaUseCase;
    private final AtualizarFuncionarioUseCase atualizarFuncionarioUseCase;
    private final RemoverFuncionarioUseCase removerFuncionarioUseCase;

    public FuncionarioController(
            CadastrarFuncionarioUseCase cadastrarFuncionarioUseCase,
            ListarFuncionarioUseCase listarFuncionarioUseCase,
            ObterFuncionarioUseCase obterFuncionarioUseCase,
            ObterFuncionarioPorPessoaUseCase obterFuncionarioPorPessoaUseCase,
            AtualizarFuncionarioUseCase atualizarFuncionarioUseCase,
            RemoverFuncionarioUseCase removerFuncionarioUseCase
    ) {
        this.cadastrarFuncionarioUseCase = cadastrarFuncionarioUseCase;
        this.listarFuncionarioUseCase = listarFuncionarioUseCase;
        this.obterFuncionarioUseCase = obterFuncionarioUseCase;
        this.obterFuncionarioPorPessoaUseCase = obterFuncionarioPorPessoaUseCase;
        this.atualizarFuncionarioUseCase = atualizarFuncionarioUseCase;
        this.removerFuncionarioUseCase = removerFuncionarioUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos(
            @RequestHeader("Authorization") String authorizationHeader) {

        List<Funcionario> funcionarios =
                listarFuncionarioUseCase.listar(authorizationHeader);

        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authorizationHeader) {

        return obterFuncionarioUseCase.obterPorId(id, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<Funcionario> buscarPorPessoaId(
            @PathVariable Long pessoaId,
            @RequestHeader("Authorization") String authorizationHeader) {

        return obterFuncionarioPorPessoaUseCase
                .obterPorPessoaId(PessoaId.of(pessoaId), authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Funcionario> salvar(
            @RequestBody Funcionario funcionario,
            @RequestHeader("Authorization") String authorizationHeader) {

        Funcionario funcionarioSalvo =
                cadastrarFuncionarioUseCase.cadastrar(funcionario, authorizationHeader);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(
            @PathVariable Long id,
            @RequestBody Funcionario funcionario,
            @RequestHeader("Authorization") String authorizationHeader) {

        return atualizarFuncionarioUseCase
                .atualizar(id, funcionario, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authorizationHeader) {

        removerFuncionarioUseCase.remover(id, authorizationHeader);
        return ResponseEntity.noContent().build();
    }
}