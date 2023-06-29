package com.evol.challenge.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Meter DTO class, used to receive data from the client.
 */
@Data
public class MeterDto {
    private long id;
    private String evolId;

    @NotNull(message = "Client id is required.")
    private long clientId;

    @NotNull(message = "Identifier is required.")
    @Size(min = 1, max = 20, message = "Identifier must be between 1 and 20 characters.")
    private String identifier;

    @NotNull(message = "Address is required.")
    @Size(min = 1, max = 255, message = "Address must be between 1 and 100 characters.")
    private String physicalAddress;

    @NotNull(message = "Installation number is required.")
    @Size(min = 1, max = 20, message = "Installation number must be between 1 and 20 characters.")
    private String installationNumber;
}
