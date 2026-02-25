package com.mypet.core.adapters.in.controllers;

import com.mypet.core.cliente.application.usecase.ClienteServiceImpl;
import com.mypet.core.cliente.domain.Cliente;
import com.mypet.core.pessoa.domain.PessoaId;
import com.mypet.core.cliente.adapter.in.dto.ClienteEnvioDTO;
import com.mypet.core.domain.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = {
        "http://45.93.100.30:4200",
        "http://192.168.15.2:4200",
        "http://192.168.15.200:4200",
        "http://localhost:4200"
})
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos(@RequestHeader("Authorization") String authorizationHeader) {
        List<Cliente> clientes = clienteService.listarTodos(authorizationHeader);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id,
                                               @RequestHeader("Authorization") String authorizationHeader) {
        return clienteService.buscarPorId(id, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody ClienteEnvioDTO dto,
                                          @RequestHeader("Authorization") String authorizationHeader) {

        // Verificação de obrigatoriedade
        if (dto.pessoaId() <= 0) {
            throw new IllegalArgumentException("pessoaId obrigatório e deve ser maior que zero");
        }

        // Converter long para LoginId
        PessoaId pessoaId = PessoaId.of(dto.pessoaId());

        // Criar Cliente usando factory method do domínio
        Cliente cliente = Cliente.novo(pessoaId, dto.clienteReg());

        // Aplicar status, se enviado
        if (dto.clienteStatus() != null && !dto.clienteStatus().isBlank()) {
            Status status = Status.valueOf(dto.clienteStatus().toUpperCase());
            if (status == Status.INATIVO) {
                cliente.inativar();
            } else {
                cliente.ativar();
            }
        }

        Cliente clienteSalvo = clienteService.salvar(cliente, authorizationHeader);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id,
                                             @RequestBody ClienteEnvioDTO dto,
                                             @RequestHeader("Authorization") String authorizationHeader) {

        if (dto.pessoaId() <= 0) {
            throw new IllegalArgumentException("pessoaId obrigatório e deve ser maior que zero");
        }

        // Converter long para LoginId
        PessoaId pessoaId = PessoaId.of(dto.pessoaId());

        // Criar Cliente com dados do DTO
        Cliente clienteAtualizado = new Cliente(
                null, // id será resolvido pelo service
                pessoaId,
                dto.clienteReg(),
                dto.clienteStatus() != null ? Status.valueOf(dto.clienteStatus().toUpperCase()) : Status.ATIVO
        );

        Cliente resultado = clienteService.atualizar(id, clienteAtualizado, authorizationHeader);
        return resultado != null ? ResponseEntity.ok(resultado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id,
                                        @RequestHeader("Authorization") String authorizationHeader) {
        clienteService.deletar(id, authorizationHeader);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<Cliente> buscarPorPessoaId(@PathVariable Long pessoaId,
                                                     @RequestHeader("Authorization") String authorizationHeader) {
        // Converter long para LoginId
        PessoaId pid = PessoaId.of(pessoaId);
        return clienteService.buscarPorPessoaId(pid, authorizationHeader)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
