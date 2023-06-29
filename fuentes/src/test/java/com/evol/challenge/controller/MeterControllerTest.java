package com.evol.challenge.controller;

import com.evol.challenge.dto.GenericResponse;
import com.evol.challenge.dto.MeterDto;
import com.evol.challenge.entity.Meter;
import com.evol.challenge.service.MeterService;
import com.evol.challenge.util.MeterDtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.evol.challenge.exception.ResourceNotFoundException;

public class MeterControllerTest {

    @Mock
    private MeterService meterService;

    @Mock
    private MeterDtoConverter converter;
    @InjectMocks
    private MeterController meterController;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMeterByIdSuccess() {
        // Arrange
        Long meterId = 1L;
        Meter meter = new Meter();
        MeterDto meterDto = new MeterDto();
        when(meterService.getById(meterId)).thenReturn(meter);
        when(converter.convertToDto(meter)).thenReturn(meterDto);
        // Act
        ResponseEntity<GenericResponse<MeterDto>> response = meterController.getMeterById(meterId);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(meterDto, response.getBody().getData());
    }

    @Test
    public void testCreateMeterSuccess() {
        // Arrange
        MeterDto meterDto = new MeterDto();
        Meter meter = new Meter();
        when(converter.convertToEntity(meterDto)).thenReturn(meter);
        when(meterService.create(meter)).thenReturn(meter);
        when(converter.convertToDto(meter)).thenReturn(meterDto);
        // Act
        ResponseEntity<GenericResponse<MeterDto>> response = meterController.createMeter(meterDto);
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(meterDto, response.getBody().getData());
    }

    @Test
    public void testUpdateMeterSuccess() {
        // Arrange
        Long meterId = 1L;
        Meter meter = new Meter();
        when(meterService.update(meterId, meter)).thenReturn(meter);
        // Act
        ResponseEntity<GenericResponse<Meter>> response = meterController.updateMeter(meterId, meter);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(meter, response.getBody().getData());
    }

    @Test
    public void testDeleteMeterSuccess() {
        // Arrange
        Long meterId = 1L;
        doNothing().when(meterService).deleteById(meterId);
        // Act
        ResponseEntity<GenericResponse<Void>> response = meterController.deleteMeter(meterId);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetMeterByIdNotFound() {
        // Arrange
        Long meterId = 1L;
        String errorMessage = "Meter not found with id: " + meterId;
        when(meterService.getById(meterId)).thenThrow(new ResourceNotFoundException(errorMessage));
        // Act and Assert
        try {
            meterController.getMeterById(meterId);
        } catch (ResourceNotFoundException ex) {
            // Assert
            assertEquals(errorMessage, ex.getMessage());
        }
    }

    @Test
    public void testUpdateMeterNotFound() {
        // Arrange
        Long meterId = 1L;
        Meter meter = new Meter();
        String errorMessage = "Meter not found with id: " + meterId;
        when(meterService.update(meterId, meter)).thenThrow(new ResourceNotFoundException(errorMessage));

        // Act and Assert
        try {
            meterController.updateMeter(meterId, meter);
        } catch (ResourceNotFoundException ex) {
            // Assert
            assertEquals(errorMessage, ex.getMessage());
        }
    }

    @Test
    public void testDeleteMeterNotFound() {
        // Arrange
        Long meterId = 1L;
        String errorMessage = "Meter not found with id: " + meterId;
        doThrow(new ResourceNotFoundException(errorMessage)).when(meterService).deleteById(meterId);
        // Act and Assert
        try {
            meterController.deleteMeter(meterId);
        } catch (ResourceNotFoundException ex) {
            // Assert
            assertEquals(errorMessage, ex.getMessage());
        }
    }

}
