package com.evol.challenge.repository;

import com.evol.challenge.entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Meter repository interface using JpaRepository,
 * is used for CRUD operations and no need to implement it,
 * only need to extend it.
 */
@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {
    boolean existsByEvolId(String evolId);
}
