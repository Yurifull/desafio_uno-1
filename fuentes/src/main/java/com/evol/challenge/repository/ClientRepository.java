package com.evol.challenge.repository;

import com.evol.challenge.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Client repository interface using JpaRepository,
 * is used for CRUD operations and no need to implement it,
 * only need to extend it.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    /**
     * Find client by rut
     * @param rut client rut
     * @return Client object
     */
    Client findByRut(String rut);

}