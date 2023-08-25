package com.mypet.mypet.controllers;

import com.mypet.mypet.domain.dto.ClienteDTO;
import com.mypet.mypet.userCase.ClienteUserCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteUserCase clienteUserCase;

    @GetMapping
    public List<ClienteDTO> findAll(){
        List<ClienteDTO> result = clienteUserCase.findAll();
      // List<Cliente> dto = result.stream().map(cliente -> new Cliente(cliente)).toList();
        return result;
    }

}
