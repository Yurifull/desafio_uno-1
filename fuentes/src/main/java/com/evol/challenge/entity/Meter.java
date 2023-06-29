package com.evol.challenge.entity;

import javax.persistence.*;
import lombok.Data;

/**
 * Meter entity class, represents the meters table in the database.
 */
@Data
@Entity
@Table(name = "meters")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "evol_id", unique = true)
    private String evolId;

    @Column(name = "physical_address", nullable = false)
    private String physicalAddress;

    @Column(name = "installation_number", nullable = false)
    private String installationNumber;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "identifier", nullable = false)
    private String identifier;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;*/
}