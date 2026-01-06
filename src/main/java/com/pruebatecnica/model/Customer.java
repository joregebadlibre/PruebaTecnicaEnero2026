package com.pruebatecnica.model;

import com.pruebatecnica.model.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "cliente_id")
public class Customer extends Person {

    @NotBlank
    @Column(name = "contrasena", nullable = false)
    private String password;

    @NotNull
    @Column(name = "estado", nullable = false)
    private Boolean active;

    public Long getCustomerId() {
        return getId();
    }

    public void setCustomerId(Long customerId) {
        setId(customerId);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
