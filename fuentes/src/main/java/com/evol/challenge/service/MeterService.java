package com.evol.challenge.service;

import com.evol.challenge.entity.Client;
import com.evol.challenge.entity.Meter;
import com.evol.challenge.exception.ResourceNotFoundException;
import com.evol.challenge.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class MeterService {

    /**
     * Client repository dependency injection
     */
    @Autowired
    private MeterRepository meterRepository;

    @Autowired
    private ClientService clientService;

    /**
     * Get all meters
     * @return List of Meter objects
     */
    public List<Meter> getAllMeters() {
        return meterRepository.findAll();
    }

    /**
     * Get meter by id
     * @param id meter id
     * @return Meter object
     */
    public Meter getById(Long id) {
        return meterRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Meter not found with id: " + id));
    }

    /**
     * Create meter with unique alphanumeric ID
     * @param meter meter object
     * @return Created meter object
     */
    public Meter create(Meter meter) {
        Client clientExist = clientService.getById(meter.getClientId());
        if (clientExist == null) {
            throw new ResourceNotFoundException("Client not found with id: " + meter.getClientId());
        }
        // Generate unique evolId
        String evolId = generateUniqueEvolId();
        meter.setEvolId(evolId);
        return meterRepository.save(meter);
    }

    /**
     * Update meter record
     * @param id meter id
     * @param updatedMeter meter object
     * @return Meter object or null when meter not found
     */
    public Meter update(Long id, Meter updatedMeter) {
        // Get meter by id or throw exception
        Meter meter = getById(id);
        // Update meter
        meter.setPhysicalAddress(
                updatedMeter.getPhysicalAddress() != null ?
                        updatedMeter.getPhysicalAddress() : meter.getPhysicalAddress()
        );
        meter.setInstallationNumber(
                updatedMeter.getInstallationNumber() != null ?
                        updatedMeter.getInstallationNumber() : meter.getInstallationNumber()
        );
        meter.setIdentifier(
                updatedMeter.getIdentifier() != null ? updatedMeter.getIdentifier() : meter.getIdentifier());
        return meterRepository.save(meter);
    }

    /**
     * Delete meter by id
     * @param id
     */
    public void deleteById(Long id) {
        Meter meter = getById(id);
        meterRepository.delete(meter);
    }

    /**
     * Generate unique evolId
     * @return unique evolId
     */
    private String generateUniqueEvolId() {
        String evolId;
        boolean isUnique;
        do {
            evolId = UUID.randomUUID().toString();
            isUnique = !meterRepository.existsByEvolId(evolId);
        } while (!isUnique);
        return evolId;
    }
}