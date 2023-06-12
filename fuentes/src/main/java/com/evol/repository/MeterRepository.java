
package com.evol.repository;

import com.evol.entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Integer> {
    
}
