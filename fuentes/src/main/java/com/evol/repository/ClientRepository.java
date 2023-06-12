
package com.evol.repository;

import com.evol.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Se indica que la clase será de tipo Repository
//Al extender de JpaRepository nos permite utilizar metodos como save(), findAll() etc sin necesidad de crearlos manualmente
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
}
