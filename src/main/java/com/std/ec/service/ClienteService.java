package com.std.ec.service;

import com.std.ec.model.dto.ClienteDTO;
import com.std.ec.model.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    List<ClienteDTO> findAll();

    ClienteDTO save(ClienteDTO cliente);

    ClienteDTO findById(Integer id);

    void delete(Cliente cliente);

    boolean existsById(Integer id);
}
