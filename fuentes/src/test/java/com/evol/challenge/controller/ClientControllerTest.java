package com.evol.challenge.controller;

import com.evol.challenge.dto.ClientDto;
import com.evol.challenge.dto.GenericResponse;
import com.evol.challenge.entity.Client;
import com.evol.challenge.exception.ResourceNotFoundException;
import com.evol.challenge.service.ClientService;
import com.evol.challenge.util.ClientDtoConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private ClientDtoConverter converter;

    @InjectMocks
    private ClientController clientController;

    @Test
    public void testGetClientByIdSuccess() {
        // Arrange
        Long clientId = 1L;
        Client client = new Client();
        ClientDto clientDto = new ClientDto();
        when(clientService.getById(clientId)).thenReturn(client);
        when(converter.convertToDto(client)).thenReturn(clientDto);
        // Act
        ResponseEntity<GenericResponse<ClientDto>> response = clientController.getClientById(clientId);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDto, response.getBody().getData());
    }

    @Test
    public void testGetClientByIdNotFound() {
        // Arrange
        Long clientId = 1L;
        String errorMessage = "Client not found with id: " + clientId;
        when(clientService.getById(clientId)).thenThrow(new ResourceNotFoundException(errorMessage));
        // Act and Assert
        try {
            clientController.getClientById(clientId);
        } catch (ResourceNotFoundException ex) {
            // Assert
            assertEquals(errorMessage, ex.getMessage());
        }
    }

    @Test
    public void testGetClientByRutSuccess() {
        // Arrange
        String clientRut = "1-9";
        Client client = new Client();
        ClientDto clientDto = new ClientDto();
        when(clientService.getByRut(clientRut)).thenReturn(client);
        when(converter.convertToDto(client)).thenReturn(clientDto);
        // Act
        ResponseEntity<GenericResponse<ClientDto>> response = clientController.getClientByRut(clientRut);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDto, response.getBody().getData());
    }

    @Test
    public void testGetClientByRutNotFound() {
        // Arrange
        String clientRut = "1-9";
        String errorMessage = "Client not found with rut: " + clientRut;
        when(clientService.getByRut(clientRut)).thenThrow(new ResourceNotFoundException(errorMessage));

        // Act and Assert
        try {
            clientController.getClientByRut(clientRut);
        } catch (ResourceNotFoundException ex) {
            // Assert
            assertEquals(errorMessage, ex.getMessage());
        }
    }

    @Test
    public void testCreateClientSuccess() {
        // Arrange
        ClientDto clientDto = new ClientDto();
        Client client = new Client();
        when(converter.convertToEntity(clientDto)).thenReturn(client);
        when(clientService.save(client)).thenReturn(client);
        when(converter.convertToDto(client)).thenReturn(clientDto);
        // Act
        ResponseEntity<GenericResponse<ClientDto>> response = clientController.createClient(clientDto);
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clientDto, response.getBody().getData());
    }

    @Test
    public void testUpdateClientSuccess() {
        // Arrange
        Long clientId = 1L;
        ClientDto clientDto = new ClientDto();
        Client client = new Client();
        when(converter.convertToEntity(clientDto)).thenReturn(client);
        when(clientService.update(clientId, client)).thenReturn(client);
        when(converter.convertToDto(client)).thenReturn(clientDto);
        // Act
        ResponseEntity<GenericResponse<ClientDto>> response = clientController.updateClient(clientId, clientDto);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDto, response.getBody().getData());
    }

    @Test
    public void testDeleteClientSuccess() {
        // Arrange
        Long clientId = 1L;
        doNothing().when(clientService).deleteById(clientId);
        // Act
        ResponseEntity<GenericResponse<ClientDto>> response = clientController.deleteClient(clientId);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateClientNotFound() {
        // Arrange
        Long clientId = 1L;
        ClientDto clientDto = new ClientDto();
        Client client = new Client();
        String errorMessage = "Client not found with id: " + clientId;
        when(converter.convertToEntity(clientDto)).thenReturn(client);
        when(clientService.update(clientId, client)).thenThrow(new ResourceNotFoundException(errorMessage));
        // Act and Assert
        try {
            clientController.updateClient(clientId, clientDto);
        } catch (ResourceNotFoundException ex) {
            // Assert
            assertEquals(errorMessage, ex.getMessage());
        }
    }

    @Test
    public void testDeleteClientNotFound() {
        // Arrange
        Long clientId = 1L;
        String errorMessage = "Client not found with id: " + clientId;
        doThrow(new ResourceNotFoundException(errorMessage)).when(clientService).deleteById(clientId);
        // Act and Assert
        try {
            clientController.deleteClient(clientId);
        } catch (ResourceNotFoundException ex) {
            // Assert
            assertEquals(errorMessage, ex.getMessage());
        }
    }

}