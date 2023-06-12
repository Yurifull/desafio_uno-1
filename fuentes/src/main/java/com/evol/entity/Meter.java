
package com.evol.entity;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "medidor")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_medidor;
    private String evol_id;
    @ManyToOne
    private Client cli_rut;
    
    private String med_direccion;
    private Integer med_numero_instalacion;
    
    @PrePersist
    public void generateUniqueCode() {
        this.evol_id = generateCode();
    }

    private String generateCode() {
        // Genera un código único alfanumérico
        // Puedes implementar tu propia lógica para generar el código
        // Aquí hay un ejemplo simple utilizando UUID

        String uuid = UUID.randomUUID().toString();
        String evol_id = uuid.replaceAll("-", "").substring(0, 10);
        return evol_id;
    }

    public Integer getId_medidor() {
        return id_medidor;
    }

    public void setId_medidor(Integer id_medidor) {
        this.id_medidor = id_medidor;
    }

    public String getEvol_id() {
        return evol_id;
    }

    public void setEvol_id(String evol_id) {
        this.evol_id = evol_id;
    }

    public Client getCli_rut() {
        return cli_rut;
    }

    public void setCli_rut(Client cli_rut) {
        this.cli_rut = cli_rut;
    }

    public String getMed_direccion() {
        return med_direccion;
    }

    public void setMed_direccion(String med_direccion) {
        this.med_direccion = med_direccion;
    }

    public Integer getMed_numero_instalacion() {
        return med_numero_instalacion;
    }

    public void setMed_numero_instalacion(Integer med_numero_instalacion) {
        this.med_numero_instalacion = med_numero_instalacion;
    }  
}
