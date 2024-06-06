package com.std.ec.Mapper;

import com.std.ec.model.dto.ClienteDTO;
import com.std.ec.model.entity.Cliente;
import org.modelmapper.ModelMapper;

public class ClienteMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static ClienteDTO mapToDto(Cliente cliente){
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public static Cliente mapToEntity(ClienteDTO clienteDto){
        return modelMapper.map(clienteDto, Cliente.class);
    }
}
