package com.evol.challenge.dto;

import com.evol.challenge.validator.ValidDate;
import com.evol.challenge.validator.ValidRut;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Client DTO class, used to receive data from the client.
 */
@Data
public class ClientDto {
    private long id;
    @NotEmpty(message = "Name is required.")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters.")
    private String name;

    @NotNull(message = "Rut is required.")
    @ValidRut(message = "Invalid RUT. Expected format: 12345678-9")
    private String rut;

    @NotEmpty(message = "Business name is required.")
    @Size(min = 1, max = 255, message = "Business name must be between 1 and 255 characters.")
    private String businessName;

    @NotNull(message = "Start date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidDate
    private LocalDate startDate;
}
