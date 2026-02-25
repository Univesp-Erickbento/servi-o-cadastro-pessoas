package com.mypet.core.pessoa.application.usecase;

import com.mypet.core.pessoa.domain.Pessoa;
import com.mypet.core.pessoa.port.in.AtualizarPessoaUseCase;
import com.mypet.core.pessoa.port.in.CadastrarPessoaUseCase;
import com.mypet.core.pessoa.port.in.ListarPessoasUseCase;
import com.mypet.core.pessoa.port.in.ObterPessoaUseCase;
import com.mypet.core.pessoa.port.in.RemoverPessoaUseCase;
import com.mypet.core.pessoa.port.out.PessoaRepositoryPort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementação dos use cases para Login.
 * Encapsula a lógica de negócio e implementa as portas de entrada.
 */
@Service
public class PessoaServiceImpl implements
        CadastrarPessoaUseCase,
        ListarPessoasUseCase,
        ObterPessoaUseCase,
        AtualizarPessoaUseCase,
        RemoverPessoaUseCase {

    private final PessoaRepositoryPort pessoaRepositoryPort;

    public PessoaServiceImpl(PessoaRepositoryPort pessoaRepositoryPort) {
        this.pessoaRepositoryPort = pessoaRepositoryPort;
    }

    /**
     * Cadastra uma nova pessoa.
     */
    @Transactional
    @Override
    public Pessoa executar(Pessoa pessoa, String authorizationHeader) {
        return pessoaRepositoryPort.save(pessoa);
    }

    /**
     * Lista todas as pessoas cadastradas.
     */
    @Override
    public List<Pessoa> executar() {
        return pessoaRepositoryPort.findAll();
    }

    /**
     * Obtém uma pessoa pelo ID.
     */
    @Override
    public Optional<Pessoa> obterPorId(Long id, String authorizationHeader) {
        return pessoaRepositoryPort.findById(id);
    }

    /**
     * Obtém uma pessoa pelo CPF.
     */
    @Override
    public Optional<Pessoa> obterPorCpf(String cpf, String authorizationHeader) {
        return pessoaRepositoryPort.findByCpf(cpf);
    }

    /**
     * Atualiza uma pessoa existente.
     */
    @Transactional
    @Override
    public Optional<Pessoa> executar(Long id, Pessoa pessoaAtualizada, String authorizationHeader) {

        return pessoaRepositoryPort.findById(id)
                .map(existing -> {

                    existing.atualizarDados(
                            pessoaAtualizada.getNome(),
                            pessoaAtualizada.getSobrenome(),
                            pessoaAtualizada.getCpf(),
                            pessoaAtualizada.getRg(),
                            pessoaAtualizada.getGenero(),
                            pessoaAtualizada.getPerfil(),
                            pessoaAtualizada.getEmail(),
                            pessoaAtualizada.getContato(),
                            pessoaAtualizada.getDataNascimento()
                    );

                    return pessoaRepositoryPort.save(existing);
                });
    }

    /**
     * Remove uma pessoa pelo ID.
     */
    @Transactional
    @Override
    public void executar(Long id, String authorizationHeader) {
        pessoaRepositoryPort.deleteById(id);
    }
}
