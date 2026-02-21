package com.mypet.core.funcionario.application.usecase;

import com.mypet.core.funcionario.domain.Funcionario;
import com.mypet.core.funcionario.domain.FuncionarioId;
import com.mypet.core.funcionario.port.in.*;
import com.mypet.core.funcionario.port.out.FuncionarioRepositoryPort;
import com.mypet.core.pessoa.domain.PessoaId;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Implementação dos use cases de Funcionário.
 */
@Service
public class FuncionarioServiceImpl implements
        CadastrarFuncionarioUseCase,
        ListarFuncionarioUseCase,
        ObterFuncionarioUseCase,
        ObterFuncionarioPorPessoaUseCase,
        AtualizarFuncionarioUseCase,
        RemoverFuncionarioUseCase {

    private final FuncionarioRepositoryPort funcionarioRepositoryPort;

    public FuncionarioServiceImpl(FuncionarioRepositoryPort funcionarioRepositoryPort) {
        this.funcionarioRepositoryPort = funcionarioRepositoryPort;
    }

    // =========================
    // CADASTRAR
    // =========================
    @Override
    @Transactional
    public Funcionario cadastrar(Funcionario funcionario, String authorizationHeader) {
        return funcionarioRepositoryPort.save(funcionario);
    }

    // =========================
    // LISTAR
    // =========================
    @Override
    public List<Funcionario> listar(String authorizationHeader) {
        return funcionarioRepositoryPort.findAll();
    }

    // =========================
    // OBTER POR ID
    // =========================
    @Override
    public Optional<Funcionario> obterPorId(Long id, String authorizationHeader) {
        return funcionarioRepositoryPort.findById(FuncionarioId.of(id));
    }

    // =========================
    // OBTER POR PESSOA ID
    // =========================
    @Override
    public Optional<Funcionario> obterPorPessoaId(PessoaId pessoaId, String authorizationHeader) {
        return funcionarioRepositoryPort.findByPessoaId(pessoaId);
    }

    // =========================
    // ATUALIZAR
    // =========================
    @Override
    @Transactional
    public Optional<Funcionario> atualizar(
            Long id,
            Funcionario funcionarioAtualizado,
            String authorizationHeader) {

        return funcionarioRepositoryPort.findById(FuncionarioId.of(id))
                .map(existing -> {

                    existing.alterarRegistro(funcionarioAtualizado.getFuncionarioReg());

                    if ("ATIVO".equals(funcionarioAtualizado.getFuncionarioStatus())) {
                        existing.ativar();
                    } else {
                        existing.inativar(funcionarioAtualizado.getDataDeDemissao());
                    }

                    return funcionarioRepositoryPort.save(existing);
                });
    }

    // =========================
    // REMOVER
    // =========================
    @Override
    @Transactional
    public void remover(Long id, String authorizationHeader) {
        funcionarioRepositoryPort.deleteById(FuncionarioId.of(id));
    }
}