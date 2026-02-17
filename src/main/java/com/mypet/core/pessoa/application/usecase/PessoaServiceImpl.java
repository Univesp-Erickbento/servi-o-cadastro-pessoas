package com.mypet.core.pessoa.application.usercase;

import com.mypet.core.pessoa.domain.PessoaEntity;
import com.mypet.core.adapters.out.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Método para salvar uma pessoa
    @Transactional
    public PessoaEntity salvar(PessoaEntity pessoa, String authorizationHeader) {
        // Use o token de autorização conforme necessário
        return pessoaRepository.save(pessoa);
    }

    // Método para listar todas as pessoas
    public List<PessoaEntity> listarTodas() {
        return pessoaRepository.findAll();
    }

    // Método para buscar uma pessoa por ID
    public Optional<PessoaEntity> buscarPorId(Long id, String authorizationHeader) {
        // Use o token de autorização conforme necessário
        return pessoaRepository.findById(id);
    }

    // Método para buscar uma pessoa por CPF
    public Optional<PessoaEntity> buscarPorCpf(String cpf, String authorizationHeader) {
        // Use o token de autorização conforme necessário
        return pessoaRepository.findByCpf(cpf);
    }

    // Método para atualizar uma pessoa existente
    @Transactional
    public PessoaEntity atualizar(Long id, PessoaEntity pessoaAtualizada, String authorizationHeader) {
        Optional<PessoasEntity> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent()) {
            PessoaEntity PessoaEntity = pessoaExistente.get();
            PessoaEntity.setNome(pessoaAtualizada.getNome());
            PessoaEntity.setSobrenome(pessoaAtualizada.getSobrenome());
            PessoaEntity.setCpf(pessoaAtualizada.getCpf());
            PessoaEntity.setRg(pessoaAtualizada.getRg());
            PessoaEntity.setGenero(pessoaAtualizada.getGenero());
            PessoaEntity.setPerfis(pessoaAtualizada.getPerfis());
            PessoaEntity.setEmail(pessoaAtualizada.getEmail());
            PessoaEntity.setContato(pessoaAtualizada.getContato());
            PessoaEntity.setDataNascimento(pessoaAtualizada.getDataNascimento());
            PessoaEntity.setDataCadastro(pessoaAtualizada.getDataCadastro());
            // Use o token de autorização conforme necessário
            return pessoaRepository.save(pessoaEntity);
        }
        return null;
    }

    // Método para deletar uma pessoa por ID
    @Transactional
    public void deletar(Long id, String authorizationHeader) {
        // Use o token de autorização conforme necessário
        pessoaRepository.deleteById(id);
    }
}
