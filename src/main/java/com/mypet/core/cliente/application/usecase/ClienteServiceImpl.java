package com.mypet.core.cliente.application.usecase;

import com.mypet.core.cliente.domain.Cliente;
import com.mypet.core.cliente.domain.ClienteId;
import com.mypet.core.pessoa.domain.PessoaId;
import com.mypet.core.cliente.port.out.ClienteRepositoryPort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ClienteServiceImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Transactional
    public Cliente salvar(Cliente cliente, String authorizationHeader) {
        // Já espera um Cliente com PessoaId corretamente setado
        return clienteRepositoryPort.save(cliente);
    }

    public List<Cliente> listarTodos(String authorizationHeader) {
        return clienteRepositoryPort.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id, String authorizationHeader) {
        return clienteRepositoryPort.findById(ClienteId.of(id));
    }

    @Transactional
    public Cliente atualizar(Long id, Cliente clienteAtualizado, String authorizationHeader) {
        Optional<Cliente> clienteExistente = clienteRepositoryPort.findById(ClienteId.of(id));

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            // Como o Cliente é "imutável" em partes, usamos métodos de negócio para alterar
            if (!cliente.getPessoaId().equals(clienteAtualizado.getPessoaId())) {
                // Se quiser permitir alteração de PessoaId
                throw new IllegalStateException("Não é permitido alterar pessoaId do cliente");
            }
            cliente.alterarRegistro(clienteAtualizado.getClienteReg());
            if (clienteAtualizado.getStatus() == null || clienteAtualizado.getStatus().equals(cliente.getStatus())) {
                // não altera status
            } else if (clienteAtualizado.getStatus().equals(com.mypet.core.domain.enums.Status.ATIVO)) {
                cliente.ativar();
            } else {
                cliente.inativar();
            }
            return clienteRepositoryPort.save(cliente);
        }
        return null;
    }

    @Transactional
    public void deletar(Long id, String authorizationHeader) {
        clienteRepositoryPort.deleteById(ClienteId.of(id));
    }

    public Optional<Cliente> buscarPorPessoaId(PessoaId pessoaId, String authorizationHeader) {
        return clienteRepositoryPort.findByPessoaId(pessoaId);
    }
}
