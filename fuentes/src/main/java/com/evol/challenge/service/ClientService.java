package com.evol.challenge.service;

import com.evol.challenge.entity.Client;
import com.evol.challenge.exception.ResourceNotFoundException;
import com.evol.challenge.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Client service class for business logic
 */
@Slf4j
@Service
public class ClientService {


    /**
     * Client repository dependency injection
     */
    @Autowired
    private ClientRepository clientRepository;

    /**
     * Get client by rut
     * @param rut client rut
     * @return Client Object or throw exception when client not found
     */
    public Client getByRut(String rut) {
        Client client = clientRepository.findByRut(rut);
        if(client == null) {
            log.info("Client not found with rut: " + rut);
            throw new ResourceNotFoundException("Client not found with rut: " + rut);
        }
        return client;
    }

    /**
     * Get client by id
     * @param id client id
     * @return Client object or throw exception when client not found
     */
    public Client getById(Long id) {
        //get client by id, throw exception if not found
       return  clientRepository.findById(id)
               .orElseThrow( () -> new ResourceNotFoundException("Client not found with id: " + id));
    }

    /**
     * Save client
     * @param client client object
     * @return Client object
     */
    public Client save(Client client) {
        Client clientExist = clientRepository.findByRut(client.getRut());
        if (clientExist != null) {
            throw new ResourceNotFoundException("Client already exist with rut: " + client.getRut());
        }
        return clientRepository.save(client);
    }

    /**
     * Update client record
     * @param client client object
     * @return Client object or null when client not found
     */
    public Client update(Long id ,Client client) {
        //get client by id, throw exception if not found
        Client existingClient = getById(id);
        //update client
        existingClient.setName(client.getName() != null ? client.getName() : existingClient.getName());
        existingClient.setRut(client.getRut() != null ? client.getRut() : existingClient.getRut());
        existingClient.setBusinessName(client.getBusinessName() != null ? client.getBusinessName() :  existingClient.getBusinessName());
        existingClient.setStartDate(client.getStartDate() != null ? client.getStartDate() : existingClient.getStartDate());
        return clientRepository.save(existingClient);

    }

    /**
     * Delete client by id
     * @param id client id
     * @return true when client was deleted, false when client not found
     */
    public void deleteById(Long id) {
        //get client by id, throw exception if not found
        Client existingClient = getById(id);
        //delete client
        clientRepository.delete(existingClient);
    }

}
