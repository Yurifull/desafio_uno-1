package com.evol.challenge.controller;

import com.evol.challenge.dto.GenericResponse;
import com.evol.challenge.dto.MeterDto;
import com.evol.challenge.entity.Meter;
import com.evol.challenge.service.MeterService;
import com.evol.challenge.util.MeterDtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/meters")
@Api(tags = "Meter API")
public class MeterController {


    @Autowired
    private  MeterService meterService;

    @Autowired
    private MeterDtoConverter converter;


    @GetMapping("/{id}")
    @ApiOperation("Get meter by ID")
    @ApiResponses( {
            @ApiResponse(code = 200, message = "Meter found"),
            @ApiResponse(code = 404, message = "Meter not found")
    })
    public ResponseEntity<GenericResponse<MeterDto>> getMeterById(@PathVariable Long id) {
        Meter meter = meterService.getById(id);
        return sendResponse(
                converter.convertToDto(meter),
                "Meter found",
                HttpStatus.OK
        );
    }

    @PostMapping
    @ApiOperation("Create a new meter")
    @ApiResponses( {
            @ApiResponse(code = 201, message = "Meter created"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<GenericResponse<MeterDto>> createMeter(@Valid @RequestBody MeterDto meter) {
        log.info("Getting meter {}", meter);
        Meter createdMeter = meterService.create(converter.convertToEntity(meter));
        return sendResponse(
                converter.convertToDto(createdMeter),
                "Meter created",
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a meter")
    @ApiResponses( {
            @ApiResponse(code = 200, message = "Meter updated"),
            @ApiResponse(code = 404, message = "Meter not found")
    })
    public ResponseEntity<GenericResponse<Meter>> updateMeter(@PathVariable Long id, @Valid @RequestBody Meter meter) {
        log.info("Updating meter with ID {}", id);
        Meter updatedMeter = meterService.update(id, meter);
        return sendResponse(updatedMeter, "Meter updated", HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a meter by ID")
    @ApiResponses( {
            @ApiResponse(code = 204, message = "Meter deleted"),
            @ApiResponse(code = 404, message = "Meter not found")
    })
    public ResponseEntity<GenericResponse<Void>> deleteMeter(@PathVariable Long id) {
        meterService.deleteById(id);
        return sendResponse(null, "Meter deleted", HttpStatus.OK);

    }

    /**
     * Send a response with the given data, message and status
     * @param data The data to send
     * @param message The message to send
     * @param status The status to send
     * @return The response DTO
     * @param <T>
     */
    private <T> ResponseEntity<GenericResponse<T>> sendResponse(T data, String message, HttpStatus status) {
        GenericResponse<T> response = new GenericResponse<>();
        response.setData(data);
        response.setCode(status.value());
        response.setMessage(message);
        return ResponseEntity.status(status).body(response);
    }
}