package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private Double monto;

    private Long cbuCuenta;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setCuenta(Long cbuCuenta) {
        this.cbuCuenta = cbuCuenta;
    }

    public Long getCuenta() {
        return cbuCuenta;
    }
}
