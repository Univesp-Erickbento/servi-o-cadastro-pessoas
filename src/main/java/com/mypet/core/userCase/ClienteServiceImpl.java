package com.mypet.mypet.userCase;

import com.mypet.mypet.application.core.domain.model.ClientesEntity;
import com.mypet.mypet.adapters.out.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClientesEntity salvar(ClientesEntity cliente, String authorizationHeader) {
        return clienteRepository.save(cliente);
    }

    public List<ClientesEntity> listarTodos(String authorizationHeader) {
        return clienteRepository.findAll();
    }

    public Optional<ClientesEntity> buscarPorId(Long id, String authorizationHeader) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public ClientesEntity atualizar(Long id, ClientesEntity clienteAtualizado, String authorizationHeader) {
        Optional<ClientesEntity> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            ClientesEntity cliente = clienteExistente.get();
            cliente.setPessoaId(clienteAtualizado.getPessoaId());
            cliente.setClienteReg(clienteAtualizado.getClienteReg());
            cliente.setClienteStatus(clienteAtualizado.getClienteStatus());
            return clienteRepository.save(cliente);
        }
        return null;
    }

    @Transactional
    public void deletar(Long id, String authorizationHeader) {
        clienteRepository.deleteById(id);
    }

    // 🔹 Novo método para buscar cliente por pessoaId
    public Optional<ClientesEntity> buscarPorPessoaId(Long pessoaId, String authorizationHeader) {
        return clienteRepository.findByPessoaId(pessoaId);
    }
}
