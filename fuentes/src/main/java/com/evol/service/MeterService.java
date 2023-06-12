
package com.evol.service;

import com.evol.entity.Meter;
import com.evol.repository.MeterRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeterService {
    
    @Autowired
    private MeterRepository meterrepository;
    
    public Meter SetInsert(Meter met){
        return meterrepository.save(met);
    }
    
    public Meter SetUpdate(Meter met){
        return meterrepository.save(met);
    }
    
    public List<Meter> GetAll(){
        return meterrepository.findAll();
    }
    
    public void SetDelete(Meter met){
        meterrepository.delete(met);
    }
}
