
package com.evol.controller;

import com.evol.entity.Client;
import com.evol.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Indica que l clase sera de tipo RestController
@RestController
//Indica que para acceder a la clase se debe agregar /client a la URL
@RequestMapping("/client")
public class ClientController {
    
    //Con @Autowired se realiza de forma automatica la inyección de dependencia con la clase ClientService
    @Autowired
    private ClientService clientservice;
    
    //Con @GetMapping, @PostMapping, @PutMapping, @DeleteMapping se indica el tipo de llamado que se está realizando
    
    //Llama al método que lista los clientes
    @GetMapping
    public List<Client> GetAll(){
        return clientservice.GetAll();
    }
    
    //Llama al método que agrega nuevos clientes
    @PostMapping
    public Client SetInsert(@RequestBody Client cli){
        return clientservice.SetInsert(cli);
    }
    
    //Llama al método que Actualiza los clientes
    @PutMapping
    public Client SetPut(@RequestBody Client cli){
        return clientservice.SetUpdate(cli);
    }
    
    //Llama al método que elimina los clientes
    @DeleteMapping
    public void SetDelete(@RequestBody Client cli){
        clientservice.SetDelete(cli);
    }

}
