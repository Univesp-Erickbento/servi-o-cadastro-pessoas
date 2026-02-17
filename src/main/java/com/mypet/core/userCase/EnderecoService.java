package com.mypet.mypet.userCase;
import com.mypet.mypet.application.core.domain.model.EnderecosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String ENDERECO_SERVICE_URL = "http://endereco-service/api/enderecos";

    public EnderecosEntity salvarEndereco(EnderecosEntity endereco) {
        return restTemplate.postForObject(ENDERECO_SERVICE_URL, endereco, EnderecosEntity.class);
    }

    public EnderecosEntity buscarEnderecoPorId(Long id) {
        return restTemplate.getForObject(ENDERECO_SERVICE_URL + "/{id}", EnderecosEntity.class, id);
    }

    public EnderecosEntity atualizarEndereco(EnderecosEntity enderecosEntity) {
        restTemplate.put(ENDERECO_SERVICE_URL + "/{id}", enderecosEntity, enderecosEntity.getId());
        return enderecosEntity;
    }

    public void deletarEndereco(Long id) {
        restTemplate.delete(ENDERECO_SERVICE_URL + "/{id}", id);
    }
}
