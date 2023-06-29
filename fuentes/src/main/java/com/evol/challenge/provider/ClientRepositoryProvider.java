package com.evol.challenge.provider;
import com.evol.challenge.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Client repository provider class permits to access to ClientRepository securely, used by validators.
 */
@Component
public class ClientRepositoryProvider {
    private static ClientRepositoryProvider instance;

    private final ClientRepository clientRepository;

    @Autowired
    public ClientRepositoryProvider(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        instance = this;
    }

    public static ClientRepository getRepository() {
        return instance.clientRepository;
    }
}