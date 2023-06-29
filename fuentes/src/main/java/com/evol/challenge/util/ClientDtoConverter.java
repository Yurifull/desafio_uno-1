package com.evol.challenge.util;

import com.evol.challenge.dto.ClientDto;
import com.evol.challenge.entity.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClientDtoConverter class, converts Client to ClientDto and vice versa
 */
@Component
public class ClientDtoConverter implements EntityConverter<Client, ClientDto> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ClientDto convertToDto(Client client) {
        return modelMapper.map(client, ClientDto.class);

    }

    @Override
    public Client convertToEntity(ClientDto clientDto) {
        return modelMapper.map(clientDto, Client.class);
    }
}