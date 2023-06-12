
package com.evol.controller;

import com.evol.entity.Meter;
import com.evol.service.MeterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meter")
public class MeterController {
    
    @Autowired
    private MeterService meterservice;
    
    @GetMapping
    public List<Meter> GetAll(){
        return meterservice.GetAll();
    }
    
    @PostMapping
    public Meter SetInsert(@RequestBody Meter met){
        return meterservice.SetInsert(met);
    }
    
    @PutMapping
    public Meter SetPut(@RequestBody Meter met){
        return meterservice.SetUpdate(met);
    }
    
    @DeleteMapping
    public void SetDelete(@RequestBody Meter met){
        meterservice.SetDelete(met);
    }
    
}
