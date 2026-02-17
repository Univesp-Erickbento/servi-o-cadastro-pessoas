package com.mypet.mypet.userCase;

import com.mypet.mypet.domain.dto.FuncionarioDTO;
import com.mypet.mypet.application.core.domain.model.FuncionariosEntity;
import com.mypet.mypet.adapters.out.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioServiceImpl {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public FuncionarioDTO salvar(FuncionarioDTO funcionarioDTO, String authorizationHeader) {
        String token = authService.getToken(authorizationHeader);
        FuncionariosEntity funcionario = convertToEntity(funcionarioDTO);
        FuncionariosEntity funcionarioSalvo = funcionarioRepository.save(funcionario);
        return convertToDto(funcionarioSalvo);
    }

    public List<FuncionarioDTO> listarTodos(String authorizationHeader) {
        return funcionarioRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<FuncionarioDTO> buscarPorId(Long id, String authorizationHeader) {
        return funcionarioRepository.findById(id)
                .map(this::convertToDto);
    }

    public Optional<FuncionarioDTO> buscarPorPessoaId(Long pessoaId, String authorizationHeader) {
        return funcionarioRepository.findByPessoaId(pessoaId)
                .map(this::convertToDto);
    }

    @Transactional
    public FuncionarioDTO atualizar(Long id, FuncionarioDTO funcionarioDTO, String authorizationHeader) {
        return funcionarioRepository.findById(id)
                .map(funcionariosEntity -> {
                    funcionariosEntity.setPessoaId(funcionarioDTO.getPessoaId());
                    funcionariosEntity.setFuncionarioTipo(funcionarioDTO.getFuncionarioTipo());
                    funcionariosEntity.setFuncionarioReg(funcionarioDTO.getFuncionarioReg());
                    funcionariosEntity.setFuncionarioStatus(funcionarioDTO.getFuncionarioStatus());
                    funcionariosEntity.setDataDeAdmissao(funcionarioDTO.getDataDeAdmissao());
                    funcionariosEntity.setDataDeDemissao(funcionarioDTO.getDataDeDemissao());
                    FuncionariosEntity funcionarioAtualizado = funcionarioRepository.save(funcionariosEntity);
                    return convertToDto(funcionarioAtualizado);
                })
                .orElse(null);
    }

    @Transactional
    public void deletar(Long id, String authorizationHeader) {
        funcionarioRepository.deleteById(id);
    }

    private FuncionarioDTO convertToDto(FuncionariosEntity funcionario) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setPessoaId(funcionario.getPessoaId());
        dto.setFuncionarioTipo(funcionario.getFuncionarioTipo());
        dto.setFuncionarioReg(funcionario.getFuncionarioReg());
        dto.setFuncionarioStatus(funcionario.getFuncionarioStatus());
        dto.setDataDeAdmissao(funcionario.getDataDeAdmissao());
        dto.setDataDeDemissao(funcionario.getDataDeDemissao());
        return dto;
    }

    private FuncionariosEntity convertToEntity(FuncionarioDTO dto) {
        FuncionariosEntity funcionario = new FuncionariosEntity();
        funcionario.setPessoaId(dto.getPessoaId());
        funcionario.setFuncionarioTipo(dto.getFuncionarioTipo());
        funcionario.setFuncionarioReg(dto.getFuncionarioReg());
        funcionario.setFuncionarioStatus(dto.getFuncionarioStatus());
        funcionario.setDataDeAdmissao(dto.getDataDeAdmissao());
        funcionario.setDataDeDemissao(dto.getDataDeDemissao());
        return funcionario;
    }
}
