package com.palazzisoft.balonpie.service.model;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.counting;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_EQUIPO")
public class Equipo implements Serializable {

    private static final long serialVersionUID = 6358813478617236529L;

    @Id
    @Column(name = "F_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "A_NOMBRE")
    private String nombre;

    @Column(name = "A_DESCRIPCION")
    private String descripcion;

    @Column(name = "A_FECHA_CREACION")
    private Date fechaCreacion;

    @Column(name = "A_ESTADO")
    private Integer estado;

    @ManyToOne(fetch = FetchType.EAGER)
    private Participante participante;

    @Column(name = "A_PRESUPUESTO")
    private Long presupuesto;

    @OneToMany(mappedBy = "equipo", fetch = FetchType.EAGER, cascade = { CascadeType.ALL}, orphanRemoval = true)
    private Set<EquipoJugador> equipoJugadores;

    @Column(name = "A_PUNTOS")
    private Long puntos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "T_EQUIPO_TORNEO",
            joinColumns = { @JoinColumn(name = "EQUIPO_F_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "TORNEO_F_ID", nullable = false, updatable = false) })
    private List<Torneo> torneos;

    public Equipo() {
        this.equipoJugadores = newHashSet();
    }

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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Long getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Long presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Set<EquipoJugador> getEquipoJugadores() {
        return equipoJugadores;
    }

    public void setEquipoJugadores(Set<EquipoJugador> equipoJugadores) {
        this.equipoJugadores = equipoJugadores;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getPuntos() {
        return puntos;
    }

    public void setPuntos(Long puntos) {
        this.puntos = puntos;
    }

    public List<Torneo> getTorneos() {
        return torneos;
    }

    public void setTorneos(List<Torneo> torneos) {
        this.torneos = torneos;
    }

    public EquipoJugador buscarEquipoJugadorPorJugador(Integer jugadorId) {
        for (EquipoJugador equipoJugador : this.equipoJugadores) {
            if (equipoJugador.getJugador().getId().equals(jugadorId)) {
                return equipoJugador;
            }
        }

        return null;
    }

    public Integer calcularPresupuestoDeEquipo() {
        return getEquipoJugadores().stream().map(EquipoJugador::getJugador).map(Jugador::getValor).collect(counting())
                .intValue();
    }

    private boolean tieneJugador(Jugador jugador) {
        for (EquipoJugador equipoJugador : this.getEquipoJugadores()) {
            Jugador jugadorDeEquipo = equipoJugador.getJugador();

            if (jugadorDeEquipo.equals(jugador)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipo other = (Equipo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
