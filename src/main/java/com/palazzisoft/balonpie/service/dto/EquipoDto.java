package com.palazzisoft.balonpie.service.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.palazzisoft.balonpie.service.model.EquipoJugador;
import com.palazzisoft.balonpie.service.model.Participante;
import com.palazzisoft.balonpie.service.model.Torneo;

public class EquipoDto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private Integer estado;
    private ParticipanteDto participante;
    private Long presupuesto;
    private Set<JugadorDto> jugadores;
    private Long puntos;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public ParticipanteDto getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteDto participante) {
        this.participante = participante;
    }

    public Long getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Long presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Set<JugadorDto> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<JugadorDto> jugadores) {
        this.jugadores = jugadores;
    }

    public Long getPuntos() {
        return puntos;
    }

    public void setPuntos(Long puntos) {
        this.puntos = puntos;
    }

}
