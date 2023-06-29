package com.evol.challenge.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.evol.challenge.validator.ValidDate;
import com.evol.challenge.validator.ValidRut;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String rut;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

}