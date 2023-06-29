package com.evol.challenge.controller;

import com.evol.challenge.dto.ClientDto;
import com.evol.challenge.dto.GenericResponse;
import com.evol.challenge.entity.Client;
import com.evol.challenge.service.ClientService;
import com.evol.challenge.util.ClientDtoConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/clients")
@Api(tags = "Client API")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientDtoConverter converter;

    @GetMapping("/{id}")
    @ApiOperation("Get client by ID")
    @ApiResponses( {
        @ApiResponse(code = 200, message = "Client found"),
        @ApiResponse(code = 404, message = "Client not found")
    })
    public ResponseEntity<GenericResponse<ClientDto>> getClientById(@PathVariable Long id) {
        Client client = clientService.getById(id);
        return sendResponse(
                converter.convertToDto(client),
                "Client found",
                HttpStatus.OK
        );
    }

    @GetMapping("/rut/{rut}")
    @ApiOperation("Get client by RUT")
    @ApiResponses( {
        @ApiResponse(code = 200, message = "Client found"),
        @ApiResponse(code = 404, message = "Client not found")
    })
    public ResponseEntity<GenericResponse<ClientDto>> getClientByRut(@PathVariable String rut) {
        Client client = clientService.getByRut(rut);
        return sendResponse(
                converter.convertToDto(client),
                "Client found",
                HttpStatus.OK
        );
    }

    @PostMapping
    @ApiOperation("Create a new client")
    @ApiResponses( {
        @ApiResponse(code = 201, message = "Client created"),
        @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<GenericResponse<ClientDto>> createClient(@Valid @RequestBody ClientDto client) {
        log.info("Creating client: {}", client);
        Client createdClient = clientService.save(converter.convertToEntity(client));
        return sendResponse(
                converter.convertToDto(createdClient),
                "Client created",
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a client")
    @ApiResponses( {
        @ApiResponse(code = 200, message = "Client updated"),
        @ApiResponse(code = 404, message = "Client not found")
    })
    public ResponseEntity<GenericResponse<ClientDto>> updateClient(@PathVariable Long id, @RequestBody ClientDto client) {
        Client updatedClient = clientService.update(id, converter.convertToEntity(client));
        return sendResponse(
                converter.convertToDto(updatedClient),
                "Client updated",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a client")
    @ApiResponses( {
        @ApiResponse(code = 200, message = "Client deleted"),
        @ApiResponse(code = 404, message = "Client not found")
    })
    public ResponseEntity<GenericResponse<ClientDto>> deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
        return sendResponse(null, "Client deleted", HttpStatus.OK);
    }

    /**
     * Handle Api responses, create a GenericResponse with generic DTO data, message and status code
     * @param message string message with the response
     * @param status http status code
     * @return ResponseEntity with the response
     */
    private <T> ResponseEntity<GenericResponse<T>> sendResponse(T data, String message, HttpStatus status) {
        GenericResponse<T> response = new GenericResponse<>();
        response.setData(data);
        response.setCode(status.value());
        response.setMessage(message);
        return ResponseEntity.status(status).body(response);
    }

}