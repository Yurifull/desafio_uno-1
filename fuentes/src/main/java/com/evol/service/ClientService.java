
package com.evol.service;

import com.evol.entity.Client;
import com.evol.repository.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//Indica que la clase será de tipo Service
@Service
public class ClientService {
    
    //Con @Autowired realiza la inyección de depencia en base a la interfaz
    @Autowired
    private ClientRepository clientrepository;
    
    //Realiza el ingreso del nuevo cliente. Nota: si ya existe lo actualiza
    public Client SetInsert(Client cli){
        return clientrepository.save(cli);
    }
    
    //Realiza la actualización del cliente. Nota: si no existe lo inserta
    public Client SetUpdate(Client cli){
        return clientrepository.save(cli);
    }
    
    //Trae una lista con todos los clientes
    public List<Client> GetAll(){
        return clientrepository.findAll();
    }
    
    //Elimina el cliente ingresado
    public void SetDelete(Client cli){
        clientrepository.delete(cli);
    }
    
}
