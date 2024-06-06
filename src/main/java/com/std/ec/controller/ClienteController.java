package com.std.ec.controller;

import com.std.ec.model.dto.ClienteDTO;
import com.std.ec.model.entity.Cliente;
import com.std.ec.model.payload.ApiResponse;
import com.std.ec.model.payload.MensajeResponse;
import com.std.ec.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.std.ec.Mapper.ClienteMapper.mapToEntity;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity<ApiResponse<Object>> consultarClientes() {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setStatus("success");
        response.setData(clienteService.findAll());
        response.setMessage("Data retrieved successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/clientes")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Object>> guardarCliente(@RequestBody ClienteDTO cliente) {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setStatus("success");
        response.setData(clienteService.save(cliente));
        response.setMessage("Data retrieved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clientes")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> actualizarCliente(@RequestBody ClienteDTO cliente) {
        if (!clienteService.existsById(cliente.getIdCliente())) {
            return new ResponseEntity<>(
                    "Cliente no encontrado con el ID: " + cliente.getIdCliente(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
    }

    @DeleteMapping("/clientes/{id}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarCliente(@PathVariable("id") Integer id) {
        try {
            Cliente clienteDelete = mapToEntity(clienteService.findById(id));
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exception.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientes/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> consultarClientePorId(@PathVariable("id") Integer id) {
        ClienteDTO cliente = clienteService.findById(id);
        if (cliente == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe una registro con el id : " + id)
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}
