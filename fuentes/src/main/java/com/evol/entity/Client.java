
package com.evol.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Client {
    
    @Id
    @Column(length = 13)
    private String cli_rut;
    private String cli_nombre;
    private String cli_razon_social;
    private Date cli_fecha_inicio_firma;

    public String getCli_rut() {
        return cli_rut;
    }

    public void setCli_rut(String cli_rut) {
        this.cli_rut = cli_rut;
    }

    public String getCli_nombre() {
        return cli_nombre;
    }

    public void setCli_nombre(String cli_nombre) {
        this.cli_nombre = cli_nombre;
    }

    public String getCli_razon_social() {
        return cli_razon_social;
    }

    public void setCli_razon_social(String cli_razon_social) {
        this.cli_razon_social = cli_razon_social;
    }

    public Date getCli_fecha_inicio_firma() {
        return cli_fecha_inicio_firma;
    }

    public void setCli_fecha_inicio_firma(Date cli_fecha_inicio_firma) {
        this.cli_fecha_inicio_firma = cli_fecha_inicio_firma;
    }
    
}
