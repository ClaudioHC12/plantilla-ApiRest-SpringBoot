package com.std.ec.service.impl;

import com.std.ec.model.dto.ClienteDTO;
import com.std.ec.model.repository.ClienteRepository;
import com.std.ec.model.entity.Cliente;
import com.std.ec.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.std.ec.Mapper.ClienteMapper.mapToDto;
import static com.std.ec.Mapper.ClienteMapper.mapToEntity;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = repository.save(mapToEntity(clienteDTO));
        return mapToDto(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public ClienteDTO findById(Integer id) {
        Cliente cliente = repository.findById(id).orElse(null);
        if (cliente == null) {
            return null;
        }
        return mapToDto(cliente);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        repository.delete(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Integer id){
        return repository.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClienteDTO> findAll(){
        List<ClienteDTO> clientes = new ArrayList<>();
        repository.findAll().forEach(cliente -> {
            clientes.add(mapToDto(cliente));
        });
        return  clientes;
    }
}
