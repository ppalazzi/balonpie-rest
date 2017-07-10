package com.palazzisoft.balonpie.service.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.palazzisoft.balonpie.service.model.EquipoJugador;
import com.palazzisoft.balonpie.service.model.TipoJugador;

public class JugadorDto {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer velocidad;
    private Integer remate;
    private Integer habilidad;
    private Integer fisico;
    private Integer estado;
    private Integer valor;
    private Integer tipoJugador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    public Integer getRemate() {
        return remate;
    }

    public void setRemate(Integer remate) {
        this.remate = remate;
    }

    public Integer getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Integer habilidad) {
        this.habilidad = habilidad;
    }

    public Integer getFisico() {
        return fisico;
    }

    public void setFisico(Integer fisico) {
        this.fisico = fisico;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getTipoJugador() {
        return tipoJugador;
    }

    public void setTipoJugador(Integer tipoJugador) {
        this.tipoJugador = tipoJugador;
    }

}
